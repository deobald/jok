(ns jok.views.pink
  (:require [jok.views.layou :as layou])
  (:use [hiccup.core :only [html]]))

(defn- song-list [song]
  [:li.queued-song
   [:div
    [:h1 (:title song)]
    [:h2 (:artist song)]
    [:h2 (:album song)]]
   [:div
    "(art)"]
   [:div.play
    [:a {:href "/pink/play"} "Play"]]
   [:div.pause
    [:a {:href "/pink/pause"} "Pause"]]])

(defn index [request songs]
  (layou/t request "pink" "yellow" "white"
           (html
            [:ul
             (map song-list songs)])))
