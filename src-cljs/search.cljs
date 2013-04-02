(ns search
  (:require [goog.ui.ac :as auto]))

(defn ready []
  (auto/createSimpleAutoComplete (clj->js ["this is one optioner" "here is another"])
                                 (.getElementById js/document "search")
                                 true))

