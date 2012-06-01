(ns jukebox-web.sex.models.white
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [jukebox-web.sex.models.tagging :as tagging]
            [jukebox-web.sex.models.utils :as utils]))

;; TODO: make configurable
(def MUSIC-DIR "music")

(defn save-file [tempfile filename]
  (let [ext (utils/extension filename)
        destination (utils/uuid-file MUSIC-DIR ext)]
    (io/copy tempfile destination)
    (tagging/correct-filename destination MUSIC-DIR)))
