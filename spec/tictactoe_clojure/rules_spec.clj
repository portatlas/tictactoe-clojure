(ns tictactoe-clojure.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.player :as player]
            [tictactoe-clojure.rules :refer :all]))

(describe "tictactoe-clojure.rules"
  (context "Any Size Board"
    (with human-x (player/create-player "Human" "X"))
    (with human-o (player/create-player "Human" "O"))
    (with players [@human-x @human-o])
  
    (describe "#switch-turn"
      (it "returns the symbol for X if O was the most recent turn"
        (should= "O" (switch-turn "X")))
      (it "returns the symbol for O if X was the most recent turn"
        (should= "X" (switch-turn "O"))))     
  
    (describe "#switch-player"
      (it "returns the second player in the vector if the first player went"
        (should= @human-o (switch-player @players @human-x)))
      (it "returns the first player in the vector if the second player went"
        (should= @human-x (switch-player @players @human-o)))))

  (context "3x3 Board"
    (with new-board [1 2 3 4 5 6 7 8 9])
    (with x-win-1st-row-board ["X" "X" "X" "O" "O" 6 7 8 9])
    (with o-win-2nd-row-board ["X" 2 "X" "O" "O" "O" 7 8 9])
    (with x-win-3rd-row-board ["X" 2 "X" "O" 5 "O" "X" "X" "X"])
    (with o-win-1st-column-board ["O" 2 "X" "O" 5 "X" "O" "X" "X"])
    (with x-win-2nd-column-board ["O" "X" "X" 4 "X" "X" "O" "X" 9])
    (with o-win-3rd-column-board [1 "X" "O" 4 5 "O" "O" "X" "O"])
    (with x-win-1st-diagonal-board ["X" 2 "O" 4 "X" "O" "O" 8 "X"])
    (with o-win-2nd-diagonal-board ["X" 2 "O" 4 "O" "X" "O" 8 "X"])
    (with board-notfull-nowinner ["X" 2 "X" "O" "O" 6 7 8 9])
    (with board-with-draw ["X" "O" "X" "O" "O" "X" "X" "X" "O"])

    (describe "#valid-move?"
      (it "returns true if the position is available in the board"
        (should= true (valid-move?  1  @new-board)))
      (it "returns false if the position is not available in the board"
        (should= false (valid-move?  1  [2 3 4 5 6 7 8 9])))
      (it "returns false if the position is out of range"
        (should= false (valid-move?  16  @new-board)))
      (it "returns false if the position is not a number"
        (should= false (valid-move?  "X" @new-board))))
  
    (describe "winner"
      (it "returns X if X is a winner in the first row"
        (should= "X" (winner @x-win-1st-row-board)))
      (it "returns O if O is a winner in the second row"
        (should= "O" (winner @o-win-2nd-row-board)))
      (it "returns X if X is a winner in the third row"
        (should= "X" (winner @x-win-3rd-row-board)))
      (it "returns O if O is a winner in the first column"
        (should= "O" (winner @o-win-1st-column-board)))
      (it "returns X if X is a winner in the second column"
        (should= "X" (winner @x-win-2nd-column-board)))
      (it "returns O if O is a winner in the third column"
        (should= "O" (winner @o-win-3rd-column-board)))	
      (it "returns X if X is a winner in the first diagonal"
        (should= "X" (winner @x-win-1st-diagonal-board))) 	
      (it "returns O if O is a winner in the second diagonal"
        (should= "O" (winner @o-win-2nd-diagonal-board)))
      (it "returns nil if the board is not full and there is no winner"
        (should= nil (winner @board-notfull-nowinner)))
      (it "returns nil if there is no winner"
        (should= nil (winner @board-with-draw))))
  
    (describe "draw?"
      (it "returns true if the board is full and there is no winner"
        (should= true (draw? @board-with-draw)))
      (it "returns false if the board is full but there is a winner"
        (should= false (draw? ["X" "X" "X" "O" "O" "X" "X" "X" "O"])))
      (it "returns false if the board is not full but there is not a winner"
        (should= false (draw? ["X" 2 "X" "O" "O" "X" "X" "X" "O"])))
      (it "returns false if the board is not full but there is a winner"
        (should= false (draw? ["X" "X" "X" "O" "O" "X" "X" 8 "O"]))))
  
    (describe "game-over?"
      (it "returns true if there is a winner"
        (should= true (game-over? @x-win-1st-row-board)))
      (it "returns true if the result is a draw"
        (should= true (game-over? @board-with-draw)))
      (it "returns false if the board is empty"
        (should= false (game-over? @new-board)))
      (it "returns false if there are open slots to be played"
        (should= false (game-over? @board-notfull-nowinner)))))

  (context "4x4 Board"
    (with new-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])
    (with x-win-1st-row-board ["X" "X" "X" "X" 5 6 7 8 9 10 11 12 13 14 15 16])

    (describe "#valid-move?"
      (it "returns true if the move is valid"
        (should= true (valid-move? 16 @new-board)))
      (it "returns false if the move is invalid"
        (should= false (valid-move? 17 @new-board))))

    (describe "winner"
      (it "returns X if X is a winner in the first row"
        (should= "X" (winner @x-win-1st-row-board))))

    (describe "game-over?"
      (it "returns true if there is a winner"
        (should= true (game-over? @x-win-1st-row-board))))))
