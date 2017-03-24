(ns tictactoe-clojure.human-player
  (:require [tictactoe-clojure.console :as console])	
  (:require [tictactoe-clojure.rules :as rules]))

(defn- to-int
  [string]
    (let [val (try 
                (Integer. (re-find #"[0-9]*" string))
                (catch Exception e "Not a number"))]
      (if (number? val)
        val
        "Not a number")))

(defn human-move
  [board]
    (let [user-move (to-int (console/prompt "Please enter your move:"))]
      (if (rules/valid-move? user-move board)
        user-move
        (do 
          (console/display "Invalid Input. Please try again.")
          (human-move board)))))
