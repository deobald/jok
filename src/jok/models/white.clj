(ns jok.models.white
  (:require [clojure.string :as string]
            [clojure.java.io :as io] 
            [jok.tagging :as tagging]
            [jok.models.utils :as utils]))

(defn save-file [tempfile filename dir]
  (let [ext (utils/extension filename)
        destination (utils/uuid-file dir ext)]
    (io/copy tempfile destination)
    (tagging/correct-filename destination dir)))
