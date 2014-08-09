(ns clj-unity.reflect-utils
  (require
    [clojure.reflect :as reflect]
    [clojure.walk :as walk])
  (import [clojure.reflect Constructor Method Field Property]))

(defn reflection-transform [x]
  (if-let [t (#{Constructor Method Field Property} (type x))]
    (into {:type t} (seq x))
    x))

(defn reflect [x & opts]
  (walk/prewalk
    reflection-transform
    (apply reflect/reflect x opts)))
