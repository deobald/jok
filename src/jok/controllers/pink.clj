(ns jok.controllers.pink
  (:require [jok.views.pink :as view]
            [jok.models.pink :as model]))

(defn index [request]
  (view/index request @model/queue))
