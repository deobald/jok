(ns jok.controllers.yellow
  (:require [ring.util.response :as response]
            [jok.config :as config]
            [jok.views.yellow :as view]
            [jok.models.yellow :as model]
            [jok.models.pink :as pink]))

(defn enqueue [request]
  (let [song (model/to-song (-> request :form-params (get "file")))]
    (pink/enqueue! song)
    (response/redirect "/")))
