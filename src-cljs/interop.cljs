(ns interop)

;; from http://www.dotkam.com/2012/11/23/convert-html5-filelist-to-clojure-vector/
(defn to-array [js-col]
  (-> (clj->js []) 
      (.-slice)
      (.call js-col)
      (js->clj)))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(extend-type js/HTMLCollection
  ISeqable
  (-seq [array] (array-seq array 0)))

(defn add-event-listener [event action]
  (.addEventListener js/document event action))

(defn log [msg]
  (.log js/console msg))

(defn dbg [where o]
  (log where)
  (log o)
  o)
