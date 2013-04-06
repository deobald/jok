(ns jok.routes
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [compojure.route :as route]
            [ring.util.response :as response]
            [jok.config :as config]
            [jok.controllers.white :as white-controller]
            [jok.controllers.yellow :as yellow-controller]
            [jok.controllers.pink :as pink-controller]
            [jok.controllers.main :as main-controller]
            [jok.views.404 :as four-oh-four]))

(defroutes roots
  (GET "/" [] main-controller/index)
  (POST "/white/upload" [] white-controller/upload)
  (POST "/white/upload-multiple" [] white-controller/upload-multiple)
  (POST "/yellow/enqueue" [] yellow-controller/enqueue)
  (GET "/pink/play" [] pink-controller/play)
  (GET "/pink/pause" [] pink-controller/pause)

  (route/files "/music" {:root config/MUSIC-DIR})
  (route/resources "/")
  (route/not-found (four-oh-four/lost)))

