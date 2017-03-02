(ns tictactoe-clojure.board)

  (def new-board [0 1 2 3 4 5 6 7 8])

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

  (defn valid-slots [board]
    (filter integer? board))

  (defn valid-move?
    [position board]
      (if (some #(= position %) (valid-slots board))
  	    true 
  	    false))

  (defn move [board position symbol]
    (if (valid-move? position board )
      (assoc new-board position symbol)
	    "Invalid Move"))