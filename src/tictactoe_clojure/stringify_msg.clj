(ns tictactoe-clojure.stringify-msg
  (:require [tictactoe-clojure.board :as board])
  (:require [tictactoe-clojure.rules :as rules]))

(def invalid-input
  "Invalid Input. Please try again.")

(defn whose-turn
  [player]
    (str "Player " (:piece player) " move\n"))

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

(defn transform-to-grid
  [board]
    (let [size (board/board-size board)]
      (let [rows (map #(column-padding % size) (partition size board))
            divider (row-padding rows)]
        (clojure.string/join divider rows))))

(defn game-result
  [board]
    (if (rules/draw? board)
      "Its a draw"
      (str "Player " (rules/winner board) " wins")))
