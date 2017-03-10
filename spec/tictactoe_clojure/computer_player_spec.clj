(ns tictactoe-clojure.computer-player-spec
  (:require [speclj.core :refer :all]
    [tictactoe-clojure.computer-player :refer :all]))

(describe "tictactoe-clojure.computer-player"
  (with x-win-1st-row-board ["X" "X" "X" "O" "O" 6 7 8 9])
  (with o-win-2nd-row-board ["X" 2 "X" "O" "O" "O" 7 8 9])

  (describe "#minimax"
  	(it "returns 100 if X are on the winning combinations"
  	  (should= 100 (minimax @x-win-1st-row-board)))
  	(it "returns -100 if O are on the winning combinations"
  	  (should= -100 (minimax @o-win-2nd-row-board)))))
