(ns browser-repl
  (:require [clojure.browser.repl :as repl]))

(defn ready []
  (repl/connect "http://localhost:9000/repl"))


