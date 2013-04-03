(ns search
  (:require [goog.ui.ac :as auto]
            [goog.dom :as gdom]
            [goog.style :as gstyle]))

;; on keypress:
;; - search song-rows for matching text
;; - hide all non-matching rows

(defn all-songs []
  (gdom/query "#songs .song"))

(defn match? [s row]
  (>= (.indexOf (gdom/getTextContent row) s) 0))

(defn show [row]
  (gstyle/showElement row true))

(defn hide [row]
  (gstyle/showElement row false))

(defn searchy [s]
  ;; if s is an empty string, show all
  (doseq [row (all-songs)]
    (if (match? s row)
      (show row)
      (hide row))))

(defn ready []
  (auto/createSimpleAutoComplete (clj->js ["this is one optioner" "here is another"])
                                 (.getElementById js/document "search")
                                 true))

