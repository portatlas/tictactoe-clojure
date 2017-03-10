(ns tictactoe-clojure.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.rules :refer :all]))

(describe "tictactoe-clojure.rules"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with x-win-top-row-board ["X" "X" "X" "O" "O" 6 7 8 9])
  (with board-with-draw ["X" "O" "X" "O" "O" "X" "X" "X" "O"])

  (describe "#valid-move?"
    (it "returns true if the position is available in the board"
      (should= true (valid-move?  1  @new-board)))
    (it "returns false if the position is not availabe in the board"
      (should= false (valid-move?  1  [2 3 4 5 6 7 8 9])))
    (it "returns false if the position supplied is out of range"
      (should= false (valid-move?  16  @new-board)))
    (it "returns false if the position supplied is not a number"
      (should= false (valid-move?  "X" @new-board))))

  (describe "#whose-turn"
    (it "returns the mark for X if O was the most recent turn"
      (should= "O" (whose-turn "X")))
    (it "returns the mark for O if X was the most recent turn"
      (should= "X" (whose-turn "O"))))	    
  
  (describe "winning-combinations"
    (it "should return the rows, columns, and diagonals"
      (should= '((1 2 3)(4 5 6)(7 8 9)
                 (1 4 7)(2 5 8)(3 6 9)
                 (1 5 9)(3 5 7)) (winning-combinations @new-board))))

  (describe "winner"
    (it "should return X if X is a winner in the first row"
      (should= "X" (winner @x-win-top-row-board)))
    (it "should return O if O is a winner in the second row"
      (should= "O" (winner ["X" 2 "X" "O" "O" "O" 7 8 9])))
    (it "should return X if X is a winner in the third row"
      (should= "X" (winner ["X" 2 "X" "O" 5 "O" "X" "X" "X"])))
    (it "should return O if O is a winner in the first column"
      (should= "O" (winner ["O" 2 "X" "O" 5 "X" "O" "X" "X"])))
    (it "should return X if X is a winner in the second column"
      (should= "X" (winner ["O" "X" "X" 4 "X" "X" "O" "X" 9])))
    (it "should return O if O is a winner in the third column"
      (should= "O" (winner [1 "X" "O" 4 5 "O" "O" "X" "O"])))	
    (it "should return X if X is a winner in the first diagonal"
      (should= "X" (winner ["X" 2 "O" 4 "X" "O" "O" 8 "X"]))) 	
    (it "should return O if O is a winner in the second diagonal"
      (should= "O" (winner ["X" 2 "O" 4 "O" "X" "O" 8 "X"])))
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
      (should= false (draw? ["X" 2 "X" "O" "O" "X" "X" "X" "O"])))
    (it "should return false if the board is not full but there is a winner"
      (should= false (draw? ["X" "X" "X" "O" "O" "X" "X" 8 "O"]))))

  (describe "game-over?"
    (it "should return true if there is a winner"
      (should= true (game-over? @x-win-top-row-board)))
    (it "should return true if the result is a draw"
      (should= true (game-over? @board-with-draw)))
    (it "should return false if the board is empty"
      (should= false (game-over? @new-board)))
    (it "should return false if there are open slots to be played"
      (should= false (game-over? ["X" 2 "X" "O" "O" "X" "X" 8 "O"])))))
