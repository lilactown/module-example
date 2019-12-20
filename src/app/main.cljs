(ns app.main
  (:require [shadow.lazy :as lazy]))


(def loadable-A (lazy/loadable app.A/A))

(def loadable-B (lazy/loadable app.B/B))


(defn ^:export start []
  ;; start loading both A and B
  (let [load-A (.then (lazy/load loadable-A)
                      ;; A also needs to load C, so run the function which
                      ;; returns promise of the value of A
                      (fn [A] (A)))
        load-B (.then (lazy/load loadable-B)
                      (fn [B] (B)))]
  (-> #js [load-A load-B]
      (js/Promise.all)
      ;; once both of them have loaded
      (.then (fn [[a b]]
               (js/console.log a)
               (js/console.log b))))))
