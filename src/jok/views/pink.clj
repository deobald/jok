(ns jok.views.pink
  (:require [jok.views.layou :as layou])
  (:use [hiccup.core :only [html]]))

(defn- song-list [song]
  [:li (:title song)])

(defn index [request songs]
  (layou/t request "pink" "yellow" "white"
           (html
            [:div.player
             [:a {:href "/pink/play"} "Play"]
             [:a {:href "/pink/pause"} "Pause"]]
            [:ul
             (map song-list songs)])))
