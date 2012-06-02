(ns jok.models.pink)

(defn new-queue []
  (clojure.lang.PersistentQueue/EMPTY))

(def queue (atom (new-queue)))

(defn enqueue! [song]
  (swap! queue #(conj % song)))

(defn current-song []
  (peek @queue))
