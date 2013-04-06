(ns jok.controllers.pink
  (:require [ring.util.response :as response]
            [jok.views.pink :as view]
            [jok.models.pink :as model]))

(defn play [request]
  (model/play)
  (response/redirect "/"))

(defn pause [request]
  (model/pause)
  (response/redirect "/"))
