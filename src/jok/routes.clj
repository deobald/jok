(ns jok.routes
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [compojure.route :as route]
            [jok.controllers.white :as white-controller]
            [jok.controllers.yellow :as yellow-controller]
            [jok.controllers.pink :as pink-controller]))

(defn lost []
  "lost!"
  ;; (html5
  ;;  [:h1
  ;;   "how did you even get here?"])
  )

(defroutes roots
  (GET "/white" [] white-controller/index)
  (POST "/white/upload" [] white-controller/upload)
  (GET "/yellow" [] yellow-controller/index)
  (POST "/yellow/enqueue" [] yellow-controller/enqueue)
  (GET "/pink" [] pink-controller/index)
  
  (route/resources "/")
  (route/not-found (lost)))

