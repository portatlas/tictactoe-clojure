(ns tictactoe-clojure.rules
  (:require [tictactoe-clojure.board :as board])
  (:require [tictactoe-clojure.symbol :refer :all]))
  
(def instructions "TicTacToe:\nTwo players take turns placing a 'X' and 'O' respectively on a 3 x 3 grid.\nThe player who succeeds in placing three of their symbols in a horizontal, vertical or diagonal row wins.\n")

(defn valid-move?
  [position board]
    (if (some #(= position %) (board/valid-slots board))
      true 
      false))

(defn switch-symbol
  [previous-turn]
    (if (= previous-turn X)
      O
      X))

(defn switch-turn
  [previous-turn]
    (if (= previous-turn X)
      O
      X))

(defn whose-turn
  [board]
    (if (> (count (filter #{X} board)) (count (filter #{O} board)))
      O
      X))

(defn switch-player 
  [players current-player]
    (if (= (first players) current-player)
      (second players)
      (first players)))

(defn winning-combinations
  [board]
    (concat
      (board/board-rows board (board/board-size board))
      (board/board-columns board (board/board-size board))
      (board/board-diagonals board (board/board-size board))))

(defn- winner-in-given-combination 
  [combination]
    (if (every? #{X} combination) X
      (if (every? #{O} combination) O)))

(defn winner 
  [board]
    (first 
      (filter #{X O} 
        (map winner-in-given-combination (winning-combinations board)))))

(defn winner?
  [board]
    (let [winner (winner board)]
      (if (or (= winner X) (= winner O))
        true
        false)))

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
