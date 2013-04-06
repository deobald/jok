(ns jok.views.main
  (:require [jok.views.layou :as layou]
            [jok.views.yellow :as library-view]
            [jok.views.pink :as queue-view])
  (:use [hiccup.core :only [html]]))

(defn arrows []
  (html [:div {:id "left-arrow"
               :onclick "javascript:window.swipey.prev();"}
         "&lt;"]
        [:div {:id "right-arrow"
               :onclick "javascript:window.swipey.next();"}
         "&gt;"]))

(defn pages [west east]
  (html
   (arrows)
   [:div {:id "slider" :class "swipe"}
    [:div {:class "swipe-wrap"}
     west
     east]]))

(defn index [request library-songs queue-songs]
  (layou/ty request
           (html
            (pages
             [:div {:id "west" :class "yellow" :data-role "page"}
              (library-view/page library-songs)]
             [:div {:id "east" :class "pink" :data-role "page"}
              (queue-view/page queue-songs)]))))


