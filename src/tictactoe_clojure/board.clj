(ns tictactoe-clojure.board)

(def new-board [1 2 3 4 5 6 7 8 9])

(defn board-rows
  [board]
    (partition 3 board))

(defn board-columns
  [board]
    (list 
      (take-nth 3 board)
      (take-nth 3 (drop 1 board))
      (take-nth 3 (drop 2 board))))

(defn board-diagonals
  [board]
    (list 
      (take-nth 4 board)
        (drop 1 (drop-last (take-nth 2 board)))))

(defn valid-slots 
  [board]
    (filter integer? board))

(defn move 
  [board position mark]
    (replace {position mark} board))

(defn stringify-board-to-grid
  [board]
    (str (nth board 0) " | " (nth board 1) " | " (nth board 2)
    "\n---------\n"
    (nth board 3) " | " (nth board 4) " | " (nth board 5)
    "\n---------\n"
    (nth board 6) " | " (nth board 7) " | " (nth board 8) "\n"))
