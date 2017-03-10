(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with x-win-1st-row-board ["X" "X" "X" "O" "O" 6 7 8 9])
  (with board-with-draw ["X" "O" "X" "O" "O" "X" "X" "X" "O"])
  (with board-displayed "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n")

  (describe "#take-two-moves"
    (it "places O on the board where specified if it is Os turn"
      (with-redefs [human-player/human-move (fn [_] 1)
                    computer-player/optimal-move nil]
        (should= ["O" 2 3 4 5 6 7 8 9] (take-two-moves @new-board "O")))))

<<<<<<< HEAD
  (describe "#show-result"
    (it "invokes #draw? to determine if there is a draw"
      (with-redefs [console/display (fn [_] nil) ]
        (should-invoke rules/draw? {:times 1} (show-result @new-board))))
    (it "displays It's a draw if the result is a draw"
      (with-redefs [console/display (fn [_] "draw")]
        (should= "draw" (show-result @board-with-draw))))
    (it "if it is not a draw #winner is invoked to check who the winner is"
      (with-redefs [console/display (fn [_] nil) ]
        (should-invoke rules/winner {:times 1} (show-result @new-board))))
    (it "it there is a winner it displays the winner"
      (with-redefs [console/display (fn [_] "X wins")]
        (should= "X wins" (show-result @x-win-1st-row-board)))))

  (describe "#play-game"
    (it "#show-result is called when the game is over"
      (with-redefs [console/display (fn [_] nil)
                    rules/game-over? (fn [_] true)
                    take-two-moves @board-with-draw]
        (should-invoke show-result {:times 1} (play-game @board-with-draw "O"))))
    (it "#show-result is not called if the game is not over"
      (with-redefs [rules/game-over? (fn [_] false)
                    human-player/human-move (fn [_] 1)
                    play-game (fn [_] nil)]
        (should-invoke show-result {:times 0} (play-game @new-board))))))
=======
  ; (describe "#take-move"
  ;   (it "places the position given by user prompt onto the game board"
  ;     (with-redefs [console/prompt (fn [_] "1")]
  ;       (should= ["X" 2 3 4 5 6 7 8 9]
  ;         (take-move @new-board)))))
  )
>>>>>>> 842f7a5... Update the index of the board to one, and a function to handle user inputs
