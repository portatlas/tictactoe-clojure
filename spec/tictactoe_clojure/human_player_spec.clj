(ns tictactoe-clojure.human-player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :refer :all]
            [tictactoe-clojure.console :as console]))

(describe "tictactoe-clojure.human-player"
  (describe "#human-move"
    (it "Returns the user move as a number if the move is in a valid slot on an empty board"
      (with-redefs [console/prompt (fn [_] "1")]
        (should= 1 (human-move [1 2 3 4 5 6 7 8 9]))))))
