(ns tictactoe-clojure.board-spec
<<<<<<< HEAD
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :refer :all]))

	(:require [speclj.core :refer :all]
		[tictactoe-clojure.board :refer :all]))

(describe "tictactoe-clojure.board"
	(with empty-board [0 1 2 3 4 5 6 7 8])

	(describe "new-board"
 		(it "returns a vector with a range from 0 to 8"
			(should= new-board @empty-board )))

 	(describe "#valid-slots"
 		(it "returns a vector of all the slots for a new board"
 			(should= @empty-board (valid-slots new-board)))
 		(it "returns a vector of the last 8 spots when the first position is taken"
 			(should= [1 2 3 4 5 6 7 8] (valid-slots ["X" 1 2 3 4 5 6 7 8])))
 		(it "returns a vector of the first row when slots in the 2nd and 3rd row are taken"
 			(should= [0 1 2] (valid-slots [0 1 2 "O" "X" "O" "X" "O" "O"])))))
