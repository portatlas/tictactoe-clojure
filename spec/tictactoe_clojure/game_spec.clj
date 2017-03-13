(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  
  (describe "#play-game"
    (it "loops through a game where the human enters 1, 2, 4 and loses to the computer"
      (with-redefs [human-player/human-move (fn [_] (with-in-str "1\n2\n4\n"))
                    console/display (fn [_] "Player O wins")]
          (should= "Player O wins" (play-game @new-board "X"))))))
