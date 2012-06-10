(ns jok.config
  (:require [clojure.java.io :as io]))

;; TODO: make configurable
(def MUSIC-DIR (.getAbsolutePath (io/file "music")))

(declare options)

(defn init [opts]
  (defonce options opts))

(defn- get-property [k]
  (or (k options)
      (->> (name k) .toUpperCase (str "JOK_") System/getenv)))

(defn stream? []
  (get-property :stream))