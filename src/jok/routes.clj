(ns jok.routes
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [compojure.route :as route]
            [jok.controllers.white :as white-controller]
            [jok.controllers.yellow :as yellow-controller]))

(defroutes roots
  (GET "/white" [] white-controller/index)
  (POST "/white/upload" [] white-controller/upload)
  (GET "/yellow" [] yellow-controller/index)
  
  (route/resources "/")
  (route/not-found "how did you even get here?"))

