(ns tictactoe-clojure.board)

(def new-board [0 1 2 3 4 5 6 7 8])

(defn valid-slots [board]
	(filter integer? board))
