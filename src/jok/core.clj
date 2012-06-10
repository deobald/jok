(ns jok.core
  (:require [clojure.tools.cli :as cli]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as adapter]
            [jok.config :as config]
            [jok.routes :as routes]))

(def app
  (-> (handler/site routes/roots)))

(defn- opts-for [args]
  (first (cli/cli args
                  ["-s" "--[no-]stream" "Stream over multicast RTP" :default false]
                  ["-p" "--port" "Port" :default 3005 :parse-fn #(Integer. %)])))

(defn -main [& args]
  (let [opts (opts-for args)]
    (config/init opts)
    (adapter/run-jetty app opts)))

;; (defn -main [& args]
;;   (with-command-line args "jok"
;;     [[port p "The port on which to run this server" "3000"]]
;;     (adapter/run-jetty app {:port (read-string port)})))
