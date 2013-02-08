(ns uploading)

(defn hello []
  (js/alert "droped"))

(.addEventListener js/document "drop" hello)