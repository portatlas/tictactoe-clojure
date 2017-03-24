(ns tictactoe-clojure.console
  (:require [tictactoe-clojure.rules :as rules]))

(defn display 
  [message]
    (println message))

(defn prompt 
  [message]
    (println message)
    (read-line))
