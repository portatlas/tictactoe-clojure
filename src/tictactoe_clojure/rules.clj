(ns tictactoe-clojure.rules
  (:require [tictactoe-clojure.board :as board]))

(defn valid-move?
  [position board]
    (if (some #(= position %) (board/valid-slots board))
      true 
      false))

(defn winning-combinations
  [board]
    (concat
      (board/board-rows board)
      (board/board-columns board)
      (board/board-diagonals board)))

(defn- winner-in-given-combination 
  [combination]
    (if (every? #{"X"} combination) "X"
      (if (every? #{"O"} combination) "O")))

(defn winner 
  [board]
    (first 
      (filter #{"X" "O"} 
        (map winner-in-given-combination (winning-combinations board)))))

(defn draw? 
  [board]
    (if (and (= (board/valid-slots board) []) (= (winner board) nil))
      true 
      false))

(defn game-over?
  [board]
    (if (or (= (draw? board) true) (not= (winner board) nil))
      true
      false))
