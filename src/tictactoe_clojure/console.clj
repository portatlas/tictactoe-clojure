(ns tictactoe-clojure.console
  (:require [tictactoe-clojure.rules :as rules]))

(defn display 
  [message]
    (println message))

(defn prompt 
  [message]
    (println message)
    (read-line))
<<<<<<< HEAD
=======

(defn- to-int
  [string]
    (Integer. (re-find #"[0-9]*" string)))

(defn prompt-user-move
  [message current-board]
    (let [user-move (to-int (prompt [message]))]
      (if (rules/valid-move? user-move current-board)
        user-move
        "Invalid Input. Please try again."))
    )
>>>>>>> 842f7a5... Update the index of the board to one, and a function to handle user inputs
