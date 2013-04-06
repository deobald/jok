(ns slide
  (:require [goog.dom :as gdom]
            goog.dom.query
            [goog.events :as events]
            [goog.events.EventType :as event-type]
            swipe 
            interop))

;; (defn find-by-classes [classes]
;;   (.item (gdom/query classes) 0))

;; (defn slide-to [color]
;;   (set! (.-location js/window) (str "/" color)))

;; (defn ready []
;;   (doseq [color ["white" "yellow" "pink"]]
;;     (if-let [e (find-by-classes (str ".sidebar." color))]
;;       (events/listen e event-type/CLICK #(slide-to color)))))

(defn ready []
  (let [slider (.getElementById js/document "slider")
        swipey (swipe.Swipe. slider {:continuous false})]
    (set! (.-swipey js/window) swipey)
    (.setup swipey)))
