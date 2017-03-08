(ns tictactoe-clojure.rules-spec
	(:require [speclj.core :refer :all]
		[tictactoe-clojure.rules :refer :all]))

(describe "tictactoe-clojure.rules"
  (with new-board [0 1 2 3 4 5 6 7 8])
  (with x-win-top-row-board ["X" "X" "X" "O" "O" 5 6 7 8])
  (with board-notfull-nowinner ["X" 1 "X" "O" "O" 5 6 7 8])
  (with board-with-draw ["X" "O" "X" "O" "O" "X" "X" "X" "O"])

  (describe "#valid-move?"
    (it "returns true if the position is available in the board"
      (should= true (valid-move?  1  @new-board)))
    (it "returns false if the position is not available in the board"
      (should= false (valid-move?  0  [1 2 3 4 5 6 7 8])))
    (it "returns false if the position supplied is out of range"
      (should= false (valid-move?  16  @new-board)))
    (it "returns false if the position supplied is not a number"
      (should= false (valid-move?  "X" @new-board))))
	    
  (describe "winning-combinations"
  	(it "should return the rows, columns, and diagonals"
  		(should= '((0 1 2)(3 4 5)(6 7 8)
                 (0 3 6)(1 4 7)(2 5 8)
                 (0 4 8)(2 4 6)) (winning-combinations @new-board))))

  (describe "winner"
  	(it "should return X if X is a winner in the first row"
  		(should= "X" (winner @x-win-top-row-board)))
  	(it "should return O if O is a winner in the second row"
  		(should= "O" (winner ["X" 1 "X" "O" "O" "O" 6 7 8])))
  	(it "should return X if X is a winner in the third row"
  		(should= "X" (winner ["X" 1 "X" "O" 4 "O" "X" "X" "X"])))
  	(it "should return O if O is a winner in the first column"
  		(should= "O" (winner ["O" 1 "X" "O" 4 "X" "O" "X" "X"])))
  	(it "should return X if X is a winner in the second column"
  		(should= "X" (winner ["O" "X" "X" 3 "X" "X" "O" "X" 8])))
  	(it "should return O if O is a winner in the third column"
  		(should= "O" (winner [0 "X" "O" 3 4 "O" "O" "X" "O"])))	
  	(it "should return X if X is a winner in the first diagonal"
  		(should= "X" (winner ["X" 1 "O" 3 "X" "O" "O" 7 "X"]))) 	
  	(it "should return O if O is a winner in the second diagonal"
  		(should= "O" (winner ["X" 1 "O" 3 "O" "X" "O" 7 "X"])))
    (it "should return nil if the board is not full and there is no winner"
      (should= nil (winner @board-notfull-nowinner)))
    (it "should return nil if there is no winner"
      (should= nil (winner @board-with-draw))))

  (describe "draw?"
    (it "should return true if the board is full and there is no winner"
      (should= true (draw? @board-with-draw)))
    (it "should return false if the board is full but there is a winner"
      (should= false (draw? ["X" "X" "X" "O" "O" "X" "X" "X" "O"])))
    (it "should return false if the board is not full but there is not a winner"
      (should= false (draw? ["X" 1 "X" "O" "O" "X" "X" "X" "O"])))
    (it "should return false if the board is not full but there is a winner"
      (should= false (draw? @board-notfull-nowinner))))

  (describe "game-over?"
    (it "should return true if there is a winner"
      (should= true (game-over? @x-win-top-row-board)))
    (it "should return true if the result is a draw"
      (should= true (game-over? @board-with-draw)))
    (it "should return false if the board is empty"
      (should= false (game-over? @new-board)))
    (it "should return false if there are open slots to be played"
      (should= false (game-over? @board-notfull-nowinner)))))
