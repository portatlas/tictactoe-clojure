(ns tictactoe-clojure.game-setup
  (:require [tictactoe-clojure.player :as player])  
  (:require [tictactoe-clojure.console :as console]))

(defn get-board-size
  []
  (let [size (console/prompt "Please pick a board size:\n3x3 (3), or\n4x4 (4), or\n5x5 (5)\n")]
    (cond
      (= size "3") 3
      (= size "4") 4
      (= size "5") 5
      :else
        (get-board-size))))

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
