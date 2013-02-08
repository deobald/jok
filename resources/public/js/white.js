// Generated by CoffeeScript 1.4.0
(function() {
  var FileNotification, Files, Notifications, PlayerNotification, Uploader,
    __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Notifications = (function() {

    function Notifications(root) {
      this.root = $(root);
      this.view = "";
    }

    Notifications.prototype.render = function() {
      this.root.append(this.view);
      return this.view;
    };

    Notifications.prototype.remove = function() {
      var $el;
      $el = $(this);
      return setTimeout(function() {
        return $el.fadeOut();
      }, 1000);
    };

    return Notifications;

  })();

  FileNotification = (function(_super) {

    __extends(FileNotification, _super);

    function FileNotification(root, template) {
      FileNotification.__super__.constructor.call(this, root);
      this.template = _.template($(template).html());
      this.root.delegate('li', 'ajax:success', this.success);
      this.root.delegate('li', 'ajax:error', this.error);
      this.root.delegate('li', 'upload:progress', this.progress);
      this.root.delegate('li', 'notification:remove', this.remove);
    }

    FileNotification.prototype.render = function(data) {
      this.view = $(this.template({
        file: data
      }));
      return FileNotification.__super__.render.apply(this, arguments);
    };

    FileNotification.prototype.success = function(e, xhr, data) {
      return $(this).addClass('success').removeClass('uploading').trigger('notification:remove');
    };

    FileNotification.prototype.error = function(e, xhr) {
      return $(this).addClass('error').removeClass('uploading').trigger('notification:remove');
    };

    FileNotification.prototype.progress = function(e, xhr, progress) {
      var percent;
      if (progress.lengthComputable) {
        percent = (progress.loaded / progress.total) * 100;
        return $('.progress-bar', this).css({
          width: "" + percent + "%"
        });
      }
    };

    return FileNotification;

  })(Notifications);

  window.FileNotification = FileNotification;

  PlayerNotification = (function() {

    function PlayerNotification() {
      this.askForPermission = __bind(this.askForPermission, this);
      if (!this.capable()) {
        return this.removeButton();
      }
      if (!this.noPermission()) {
        this.renderButton();
      }
      this.defaultOptions = {
        url: '',
        title: 'Now Playing',
        body: ''
      };
      $('#enable-notifications').bind('click', this.askForPermission);
    }

    PlayerNotification.prototype.render = function(options) {
      var body, notification, title, url, _ref;
      if (!this.capable() || this.noPermission()) {
        return false;
      }
      _ref = $.extend(this.defaultOptions, options), url = _ref.url, title = _ref.title, body = _ref.body;
      notification = window.webkitNotifications.createNotification(url, title, body);
      notification.show();
      return setTimeout(function() {
        return notification.cancel();
      }, 5000);
    };

    PlayerNotification.prototype.renderButton = function() {
      return $('#enable-notifications').text('Notifications Enabled').unbind('click');
    };

    PlayerNotification.prototype.removeButton = function() {
      $('#enable-notifications').parent('li').remove();
      return false;
    };

    PlayerNotification.prototype.capable = function() {
      return window.webkitNotifications != null;
    };

    PlayerNotification.prototype.noPermission = function() {
      return window.webkitNotifications.checkPermission() > 0;
    };

    PlayerNotification.prototype.askForPermission = function() {
      var self;
      self = this;
      return window.webkitNotifications.requestPermission(function() {
        return self.renderButton();
      });
    };

    return PlayerNotification;

  })();

  $(function() {
    return window.PlayerNotification = new PlayerNotification;
  });

  Uploader = (function() {

    function Uploader(settings) {
      this.settings = settings;
    }

    Uploader.prototype.send = function(file, $element) {
      var data, event, xhr, _i, _len, _ref;
      xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
          if (xhr.status < 300) {
            return $element.trigger('ajax:success', [xhr, xhr.responseText]);
          } else {
            return $element.trigger('ajax:error', xhr);
          }
        }
      };
      if (xhr.upload != null) {
        _ref = ["abort", "error", "load", "loadstart", "progress"];
        for (_i = 0, _len = _ref.length; _i < _len; _i++) {
          event = _ref[_i];
          xhr.upload.addEventListener(event, function(p) {
            return $element.trigger("upload:" + event, [xhr, p]);
          }, false);
        }
      }
      xhr.open(this.settings.method, this.settings.url);
      data = new FormData();
      data.append("file", file);
      return xhr.send(data);
    };

    return Uploader;

  })();

  window.Uploader = Uploader;

  Files = (function() {

    function Files() {
      this.render = __bind(this.render, this);
      this.loggedIn = $('.logged-in').length;
      this.validFiles = $('body').attr('data-accept');
      this.notification = new FileNotification('#notifications', '#file-notification');
      this.uploader = new Uploader({
        method: 'POST',
        url: '/white/upload'
      });
      document.addEventListener("dragenter", this.stopActions, false);
      document.addEventListener("dragexit", this.stopActions, false);
      document.addEventListener("dragover", this.stopActions, false);
      document.addEventListener("drop", this.stopActions, false);
      document.addEventListener("drop", this.render, false);
    }

    Files.prototype.isAcceptable = function(type) {
      return new RegExp(this.validFiles, 'gi').test(type);
    };

    Files.prototype.render = function(evt) {
      var $element, file, _i, _len, _ref, _results;
      _ref = evt.dataTransfer.files;
      _results = [];
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        file = _ref[_i];
        $element = this.notification.render({
          name: file.name,
          size: this.sizeInMb(file.size)
        });
        if (this.isAcceptable(file.type)) {
          _results.push(this.uploader.send(file, $element));
        } else {
          _results.push($element.trigger('ajax:error', ["file type is invalid"]));
        }
      }
      return _results;
    };

    Files.prototype.sizeInMb = function(size) {
      return Math.round(parseInt(size) / 1048576);
    };

    Files.prototype.stopActions = function(evt) {
      evt.stopPropagation();
      return evt.preventDefault();
    };

    return Files;

  })();

  $(function() {
    return new Files;
  });

}).call(this);
