(ns jok.controllers.main
  (:require [ring.util.response :as response]
            [jok.config :as config]
            [jok.views.main :as view]
            [jok.models.yellow :as library-model]
            [jok.models.pink :as queue-model]))

(defn index [request]
  (view/index request
              (library-model/all-songs config/MUSIC-DIR)
              @queue-model/queue))

