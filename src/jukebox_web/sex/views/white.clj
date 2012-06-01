(ns jukebox-web.sex.views.white
  (:require [jukebox-web.sex.views.layou :as layou])
  (:use [hiccup.core :only [html]]))

(defn- progress-bar []
  (html [:script#file-notification {:type "text/example" }
         [:li.uploading.alert-message.block-message
          [:p "{{ file.name }} {{ file.size }}mb"]
          [:div.progress-wrapper
           [:div.progress-bar]]]]))

(defn index [request]
  (layou/t request "white"
           [:div
            [:center
             [:div.dropbox
              "drop files here."]]
            (progress-bar)]))

