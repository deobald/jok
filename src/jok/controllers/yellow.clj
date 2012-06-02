(ns jok.controllers.yellow
  (:require [jok.config :as config]
            [jok.views.yellow :as view]
            [jok.models.yellow :as model]))

(defn index [request]
  (view/index request (model/all-songs config/MUSIC-DIR)))

