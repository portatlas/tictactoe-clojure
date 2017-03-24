(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with x-win-board ["X" "X" "X" 4 "O" 6 7 8 "O"])
  (with x-1-move-from-win-board [1 "X" "X" 4 "O" 6 "X" "X" 9])
  (with player "X")
  
  (describe "#play-game"
    (it "end the game loop and displays the winner if someone wins"
      (with-redefs [rules/game-over? (fn [_] true)
                    console/display (fn [_] "Player O wins")]
          (should= "Player O wins" (play-game @x-win-board "X"))))
    (it "loops through a game once and takes two turns"
      (with-redefs [human-player/human-move (fn [_] 1)
                    computer-player/optimal-move (fn [_ _ _] 9)
                    console/display (fn [_] "Player X wins")]
        (should= "Player X wins" (play-game @x-1-move-from-win-board "O"))))))
