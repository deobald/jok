(ns search
  (:require [clojure.browser.event :as event]
            [clojure.browser.dom :as dom]
            [clojure.string :as string]
            [goog.ui.ac :as auto]
            [goog.dom :as gdom]
            [goog.style :as gstyle]
            interop))

(defn all-song-rows []
  (gdom/query "#songs .song-row"))

(defn column-text [c]
  (string/lower-case (gdom/getTextContent c)))

(defn match-column? [s column]
  (>= (.indexOf (column-text column) (string/lower-case s)) 0))

(defn match? [s row]
  (let [f (fn [was field] (or was (match-column? s field)))]
    (reduce f false (gdom/getChildren row))))

(defn show [row]
  (gstyle/showElement row true))

(defn hide [row]
  (gstyle/showElement row false))

(defn searchy [s]
  (doseq [row (all-song-rows)]
    (if (match? s row)
      (show row)
      (hide row))))

(defn ready []
  (let [search-box (dom/get-element :search)]
    (event/listen search-box "keyup" #(searchy (.-value search-box)))))

