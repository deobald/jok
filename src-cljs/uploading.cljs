(ns uploading)

;; from http://www.dotkam.com/tag/clojurescript/
(defn to-array [js-col]
  (-> (clj->js []) 
      (.-slice)
      (.call js-col)
      (js->clj)))

(defn stop-actions [event]
  (.stopPropagation event)
  (.preventDefault event))

(defn send-file [file]
  (let [xhr (js/XMLHttpRequest.)
        data (doto (js/FormData.) (.append "file" file))]
    ;; TODO: success/error callbacks/notifications
    (.open xhr "POST" "/white/upload")
    (.send xhr data)))

(defn render [event]
  (doseq [file (to-array (-> event .-dataTransfer .-files))]
    (send-file file)))

(defn listen-to-events []
  (doto js/document
    (.addEventListener "dragenter" stop-actions)
    (.addEventListener "dragexit" stop-actions)
    (.addEventListener "dragover" stop-actions)
    (.addEventListener "drop" stop-actions)
    (.addEventListener "drop" render)))

(defn initialize []
  (listen-to-events))

(initialize)