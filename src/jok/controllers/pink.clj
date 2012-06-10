(ns jok.controllers.pink
  (:require [ring.util.response :as response]
            [jok.views.pink :as view]
            [jok.models.pink :as model]))

(defn index [request]
  (view/index request @model/queue))

(defn play [request]
  (model/play)
  (response/redirect "/pink"))

(defn pause [request]
  (model/pause)
  (response/redirect "/pink"))
