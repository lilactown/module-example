(ns app.B
  (:require [shadow.lazy :as lazy]))

(def loadable-C (lazy/loadable app.C/C))

(defn B []
  (-> (lazy/load loadable-C)
      (.then (fn [C]
               (str "B" C)))))
