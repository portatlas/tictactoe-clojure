(ns tictactoe-clojure.game-setup
	(:require [tictactoe-clojure.board :as board])  
	(:require [tictactoe-clojure.human-player :as human-player])
    (:require [tictactoe-clojure.computer-player :as computer-player])
	(:require [tictactoe-clojure.console :as console]))

(defn opponent-type
  []
  (let [opponent (console/prompt "Please choose a game mode:\nHuman vs Human (1) or\nComputer Vs Human (2)")]
    (cond
      (= opponent "1") "Human"
      (= opponent "2") "Computer"
      :else
        (do
          (console/display "Invalid Input. Please try again.")
          (opponent-type)))))

(defn opponent-sequence
  []
  (let [sequence (console/prompt "Choose who goes first,\nHuman player (1) or\nComputer player (2)")]
    (cond
      (= sequence "1") "Human"
      (= sequence "2") "Computer"
      :else
        (do
          (console/display "Invalid Input. Please try again.")
          (opponent-sequence)))))
