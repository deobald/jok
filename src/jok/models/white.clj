(ns jok.models.white
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [jok.models.tagging :as tagging]
            [jok.models.utils :as utils]))

;; TODO: make configurable
(def MUSIC-DIR "music")

(defn save-file [tempfile filename]
  (let [ext (utils/extension filename)
        destination (utils/uuid-file MUSIC-DIR ext)]
    (io/copy tempfile destination)
    (tagging/correct-filename destination MUSIC-DIR)))
