(ns jok.views.white
  (:require [jok.views.layou :as layou])
  (:use [hiccup.core :only [html]]))

(defn- progress-bar []
  (html [:script#file-notification {:type "text/example" }
         [:li.uploading.alert-message.block-message
          [:p "{{ file.name }} {{ file.size }}mb"]
          [:div.progress-wrapper
           [:div.progress-bar]]]]))
