(ns jukebox-web.sex.views.white
  (:require [jukebox-web.sex.views.layou :as layou]))

(defn index [request]
  (layou/t request
           [:div
            "hello!"]))
