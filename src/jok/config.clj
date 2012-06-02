(ns jok.config
  (:require [clojure.java.io :as io]))

;; TODO: make configurable
(def MUSIC-DIR (.getAbsolutePath (io/file "music")))

