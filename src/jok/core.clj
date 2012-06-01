(ns jok.core
  (:use ;; TODO: switch to gaz's super powerful cli lib
        clojure.contrib.command-line)
  (:require [compojure.handler :as compojure]
            [ring.middleware.flash :as flash]
            [ring.middleware.cors :as cors]
            [ring.adapter.jetty :as adapter]

            [jok.routes :as routes]
            ;;[jukebox-player.core :as player]
            ;;[jukebox-web.models.playlist :as playlist]
            ))

;; TODO: [pink] revisit
;; (player/start (playlist/playlist-seq))

(def app
  (-> (compojure/site routes/roots
                      {:session
                       {:cookie-attrs
                        {:max-age 28800}
                        :cookie-name
                        "jukebox"}})
      (cors/wrap-cors :access-control-allow-origin
                      #".*")
      flash/wrap-flash))

(defn -main [& args]
  (with-command-line args "jok"
    [[port p "The port on which to run this server" "3000"]]
    (adapter/run-jetty app {:port (read-string port)})))
