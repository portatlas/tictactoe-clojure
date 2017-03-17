(ns tictactoe-clojure.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.rules :refer :all]))

(describe "tictactoe-clojure.rules"
  (context "Any Size Board"
    (with first-player #tictactoe_clojure.player.Human{:piece "X"} )
    (with second-player #tictactoe_clojure.player.Human{:piece "O"})
    (with players [@first-player @second-player])
  
    (describe "#switch-turn"
      (it "returns the mark for X if O was the most recent turn"
        (should= "O" (switch-turn "X")))
      (it "returns the mark for O if X was the most recent turn"
        (should= "X" (switch-turn "O"))))     
  
    (describe "#switch-player"
      (it "return the second player in the vector if the first player went"
        (should= @second-player (switch-player @players @first-player)))
      (it "return the first player in the vector if the second player went"
        (should= @first-player (switch-player @players @second-player))))   )

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
      (it "returns false if the position supplied is out of range"
        (should= false (valid-move?  16  @new-board)))
      (it "returns false if the position supplied is not a number"
        (should= false (valid-move?  "X" @new-board))))
  
    (describe "winner"
      (it "should return X if X is a winner in the first row"
        (should= "X" (winner @x-win-1st-row-board)))
      (it "should return O if O is a winner in the second row"
        (should= "O" (winner @o-win-2nd-row-board)))
      (it "should return X if X is a winner in the third row"
        (should= "X" (winner @x-win-3rd-row-board)))
      (it "should return O if O is a winner in the first column"
        (should= "O" (winner @o-win-1st-column-board)))
      (it "should return X if X is a winner in the second column"
        (should= "X" (winner @x-win-2nd-column-board)))
      (it "should return O if O is a winner in the third column"
        (should= "O" (winner @o-win-3rd-column-board)))	
      (it "should return X if X is a winner in the first diagonal"
        (should= "X" (winner @x-win-1st-diagonal-board))) 	
      (it "should return O if O is a winner in the second diagonal"
        (should= "O" (winner @o-win-2nd-diagonal-board)))
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
        (should= true (game-over? @x-win-1st-row-board)))
      (it "should return true if the result is a draw"
        (should= true (game-over? @board-with-draw)))
      (it "should return false if the board is empty"
        (should= false (game-over? @new-board)))
      (it "should return false if there are open slots to be played"
        (should= false (game-over? @board-notfull-nowinner))))
  
    (describe "game-result"
      (it "should return Its a draw if the result is a draw"
        (should= "Its a draw" (game-result @board-with-draw)))
      (it "should return who the winner is if X won"
        (should= "Player X wins" (game-result @x-win-1st-row-board)))
      (it "should return who the winner is if O won"
        (should= "Player O wins" (game-result @o-win-2nd-row-board)))))

  (context "4x4 Board"
    (with new-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])
    (with x-win-1st-row-board ["X" "X" "X" "X" 5 6 7 8 9 10 11 12 13 14 15 16])

    (describe "#valid-move?"
      (it "returns true if the move is valid"
        (should= true (valid-move? 16 @new-board)))
      (it "returns false if the move is valid"
        (should= false (valid-move? 17 @new-board))))

    (describe "winner"
      (it "should return X if X is a winner in the first row"
        (should= "X" (winner @x-win-1st-row-board))))

    (describe "game-over?"
      (it "should return true if there is a winner"
        (should= true (game-over? @x-win-1st-row-board))))))
