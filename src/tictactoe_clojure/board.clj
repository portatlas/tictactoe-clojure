(ns tictactoe-clojure.board
  (:require [tictactoe-clojure.console :as console])
  (:require [clojure.math.numeric-tower :as math]))

(defn board-size
  [board]
    (math/sqrt (count board)))

(defn new-board 
  [size]
    (vec (range 1 (inc (* size size)))))

(defn board-rows
  [board size]
    (partition size board))

(defn board-columns
  [board size]
    (take size
      (list 
        (take-nth size board)
        (take-nth size (drop 1 board))
        (take-nth size (drop 2 board))
        (take-nth size (drop 3 board))
        (take-nth size (drop 4 board)))))

(defn- first-board-diagonal
  [board size]
    (take-nth (inc size) board))

(defn- second-board-diagonal
  [board size]
    (take-nth (dec size) 
      (drop-last (dec size) 
      (drop (dec size) board))))

(defn board-diagonals
  [board size]
    (list 
      (first-board-diagonal board size)
      (second-board-diagonal board size)))

(defn valid-slots 
  [board]
    (filter integer? board))

(def memo-valid-slots (memoize valid-slots))

(defn move 
  [board position mark]
    (replace {position mark} board))

(defn- add-space 
  [numchar]
    (if (= (count (str numchar)) 1)
      (str numchar " ") 
      numchar))

(defn- column-padding 
  [row size]
    (if (> size 3)
      (str " " (clojure.string/join " | " (map add-space row)) " ")
      (str " " (clojure.string/join " | " row) " ")))

(defn- row-padding
  [string]
    (str "\n" (clojure.string/join (repeat (count (first string)) "-")) "\n"))

(defn stringify-board-to-grid 
  [board]
    (let [size (board-size board)]
      (let [rows (map #(column-padding % size) (partition size board))
            divider (row-padding rows)]
        (clojure.string/join divider rows))))
