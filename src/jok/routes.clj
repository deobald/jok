(ns jok.routes
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [compojure.route :as route]
            [ring.util.response :as response]
            [jok.config :as config]
            [jok.controllers.white :as white-controller]
            [jok.controllers.yellow :as yellow-controller]
            [jok.controllers.pink :as pink-controller]
            [jok.views.404 :as four-oh-four]))

(defroutes roots
  (GET "/" [] (response/redirect "/yellow"))
  (GET "/white" [] white-controller/index)
  (POST "/white/upload" [] white-controller/upload)
  (GET "/yellow" [] yellow-controller/index)
  (POST "/yellow/enqueue" [] yellow-controller/enqueue)
  (GET "/pink" [] pink-controller/index)
  (GET "/pink/play" [] pink-controller/play)
  (GET "/pink/pause" [] pink-controller/pause)

  (route/files "/music" {:root config/MUSIC-DIR})
  (route/resources "/")
  (route/not-found (four-oh-four/lost)))

