(ns jok-core
  (:require slide
            search
            uploading
            browser-repl))

(defn ready []
  (slide/ready)
  (search/ready)
  (uploading/ready)
  (browser-repl/ready))
