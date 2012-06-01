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
      url: '/library/upload'

    document.addEventListener "dragenter", @stopActions, false
    document.addEventListener "dragexit", @stopActions, false
    document.addEventListener "dragover", @stopActions, false
    document.addEventListener "drop", @stopActions, false

    # if @loggedIn
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
