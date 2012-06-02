(ns jok.views.pink
  (:require [jok.views.layou :as layou])
  (:use [hiccup.core :only [html]]))

(defn- song-list [song]
  [:li (:title song)])

(defn index [request songs]
  (layou/t request "pink"
           [:ul 
            (map song-list songs)]))
