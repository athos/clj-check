(ns clj-check.check
  (:require
   [bultitude.core :as bultitude]
   [clojure.java.io :as io]))

(defn check [source-paths]
  (let [source-files (->> (or (seq source-paths) ["src"])
                          (map io/file))
        nses         (bultitude/namespaces-on-classpath
                      :classpath source-files
                      :ignore-unreadable? false)]
    (let [failures (atom 0)]
      (doseq [ns nses]
        (let [ns-file (-> (str ns)
                          (.replace \- \_)
                          (.replace \. \/))]
          (binding [*out* *err*]
            (println "Compiling namespace" ns))
          (try
            (binding [*warn-on-reflection* true]
              (load ns-file))
            (catch ExceptionInInitializerError e
              (swap! failures inc)
              (.printStackTrace e)))))
      (if-not (zero? @failures)
        (System/exit @failures)))))

(defn -main [& source-paths]
  (check source-paths))
