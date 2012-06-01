(ns jukebox-web.sex.routes
  (:use [compojure.core :only [defroutes GET]])
  (:require [compojure.route :as route]
            [jukebox-web.sex.controllers.white :as white-controller]))

(defroutes roots
  (GET "/white" [] white-controller/index)
  (route/resources "/")
  (route/not-found "how did you even get here?"))

