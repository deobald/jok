class Notifications

  constructor: (root) ->
    @root = $(root)
    @view = ""

  render: ->
    @root.append @view
    @view

  remove: ->
    $el = $(this)
    setTimeout( ->
      $el.fadeOut()
    , 1000)

class FileNotification extends Notifications

  constructor: (root, template) ->
    super root
    
    @template = _.template $(template).html()

    @root.delegate 'li', 'ajax:success', @success
    @root.delegate 'li', 'ajax:error', @error
    @root.delegate 'li', 'upload:progress', @progress
    @root.delegate 'li', 'notification:remove', @remove

  render: (data) ->
    @view = $ @template {file: data}
    super

  success: (e, xhr, data) ->
    $(this).addClass('success').removeClass('uploading').trigger('notification:remove')

  error: (e, xhr) ->
    $(this).addClass('error').removeClass('uploading').trigger('notification:remove')

  progress: (e, xhr, progress) ->
    if progress.lengthComputable
      percent = (progress.loaded / progress.total) * 100
      $('.progress-bar', this).css({width: "#{percent}%"})

window.FileNotification = FileNotification

class PlayerNotification

  constructor: ->
    return @removeButton() unless @capable()
    @renderButton() unless @noPermission()
    @defaultOptions =
      url: ''
      title: 'Now Playing'
      body: ''
    $('#enable-notifications').bind('click', @askForPermission)

  render: (options) ->
    return false if !@capable() or @noPermission()

    {url, title, body} = $.extend @defaultOptions, options
    notification = window.webkitNotifications.createNotification(url, title, body)
    notification.show()
    setTimeout ->
      notification.cancel()
    , 5000

  renderButton: ->
    $('#enable-notifications').text('Notifications Enabled').unbind('click')

  removeButton: ->
    $('#enable-notifications').parent('li').remove()
    false

  capable: ->
    window.webkitNotifications?

  noPermission: ->
    window.webkitNotifications.checkPermission() > 0

  askForPermission: =>
    self = this
    window.webkitNotifications.requestPermission ->
      self.renderButton()

$ -> window.PlayerNotification = new PlayerNotification


class Uploader

  constructor: (@settings) ->

  send: (file, $element) ->
    xhr = new XMLHttpRequest()

    xhr.onreadystatechange = ->
      if xhr.readyState is 4
        if xhr.status < 300
          $element.trigger 'ajax:success', [xhr, xhr.responseText]
        else
          $element.trigger 'ajax:error', xhr

    if xhr.upload?
      for event in ["abort", "error", "load", "loadstart", "progress"]
        xhr.upload.addEventListener(event, (p) ->
          $element.trigger "upload:#{event}", [xhr, p]
        , false)

    xhr.open @settings.method, @settings.url

    data = new FormData()
    data.append "file", file
    xhr.send data

window.Uploader = Uploader

class Files

  constructor: ->
    @loggedIn = $('.logged-in').length
    @validFiles = $('body').attr('data-accept')
    @notification = new FileNotification '#notifications', '#file-notification'
    @uploader = new Uploader
      method: 'POST'
      url: '/white/upload'

    document.addEventListener "dragenter", @stopActions, false
    document.addEventListener "dragexit", @stopActions, false
    document.addEventListener "dragover", @stopActions, false
    document.addEventListener "drop", @stopActions, false
    document.addEventListener "drop", @render, false

  isAcceptable: (type) ->
    new RegExp(@validFiles, 'gi').test(type)

  render: (evt) =>
    for file in evt.dataTransfer.files
      $element = @notification.render
        name: file.name
        size: @sizeInMb(file.size)
      if @isAcceptable file.type
        @uploader.send file, $element
      else
        $element.trigger 'ajax:error', ["file type is invalid"]

  sizeInMb: (size) ->
    Math.round parseInt(size) / 1048576

  stopActions: (evt) ->
    evt.stopPropagation()
    evt.preventDefault()

$ -> new Files
