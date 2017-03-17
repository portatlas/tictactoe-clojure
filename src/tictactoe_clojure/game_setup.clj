(ns tictactoe-clojure.game-setup
  (:require [tictactoe-clojure.player :as player])  
	(:require [tictactoe-clojure.console :as console]))

(declare opponent-sequence)

(defn opponent-type []
  (let [opponents (console/prompt "Please choose a game mode:\nHuman vs Human (1) or\nComputer Vs Human (2)")]
    (cond
      (= opponents "1") 
        ["Human" "Human"]
      (= opponents "2") 
        (opponent-sequence)
      :else
        (do
          (console/display "Invalid Input. Please try again.")
          (opponent-type)))))

(defn- opponent-sequence []
  (let [sequence (console/prompt "Choose who goes first,\nHuman player (1) or\nComputer player (2)")]
    (cond
      (= sequence "1") ["Human" "Computer"]
      (= sequence "2") ["Computer" "Human"]
      :else
        (do
          (console/display "Invalid Input. Please try again.")
          (opponent-sequence)))))

(defn matchup-opponents 
  [players]
    [(player/create-player (first players) "X") 
     (player/create-player (second players) "O")])

