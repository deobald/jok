(ns jok.views.main
  (:require [jok.views.layou :as layou]
            [jok.views.yellow :as library-view]
            [jok.views.pink :as queue-view])
  (:use [hiccup.core :only [html]]))

(defn pages [west east]
  [:div {:id "slider" :class "swipe"}
   [:div {:class "swipe-wrap"}
    west
    east]])

(defn index [request library-songs queue-songs]
  (layou/t request "pink" "yellow" "white"
           (html
            (pages
             [:div {:id "west" :data-role "page"}
              (library-view/page library-songs)]
             [:div {:id "east" :data-role "page"}
              (queue-view/page queue-songs)]))))


