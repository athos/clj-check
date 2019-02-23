(ns clj-check.check
  (:require
   [bultitude.core :as bultitude]
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn- file-for [ns] (-> ns name (str/replace \- \_) (str/replace \. \/)))

(defn- reflection-error
  [ns]
  (binding [*out* *err*]
    (println "Compiling namespace" ns))
  (try
    (binding [*warn-on-reflection* true]
      (load (file-for ns)))
    (catch ExceptionInInitializerError e
      (doto e .printStackTrace))))

(defn check
  [source-paths]
  (let [namespaces (bultitude/namespaces-on-classpath
                    :classpath (map io/file source-paths)
                    :ignore-unreadable? false)
        failures   (count
                    (sequence
                     (comp (map reflection-error) (remove nil?))
                     namespaces))]
    (when-not (zero? failures)
      (System/exit failures))))

(defn -main [& source-paths]
  (check (or (seq source-paths) ["src"])))
