(ns interop)

(defn add-event-listener [event action]
  (.addEventListener js/document event action))
