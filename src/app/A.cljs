(ns app.A
  (:require [shadow.lazy :as lazy]))

(def loadable-C (lazy/loadable app.C/C))

(defn A []
  (-> (lazy/load loadable-C)
      (.then (fn [C]
               (str "A" C)))))
