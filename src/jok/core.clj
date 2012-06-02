(ns jok.core
  (:require [compojure.handler :as handler]
            [ring.adapter.jetty :as adapter]
            [jok.routes :as routes]
            ;;[jukebox-player.core :as player]
            ;;[jukebox-web.models.playlist :as playlist]
            ))

;; TODO: [pink] revisit
;; (player/start (playlist/playlist-seq))

(def app
  (-> (handler/site routes/roots)))

;; (defn -main [& args]
;;   (with-command-line args "jok"
;;     [[port p "The port on which to run this server" "3000"]]
;;     (adapter/run-jetty app {:port (read-string port)})))
