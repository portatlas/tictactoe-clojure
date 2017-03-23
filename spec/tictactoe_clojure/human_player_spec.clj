(ns tictactoe-clojure.human-player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.human-player :refer :all]
            [tictactoe-clojure.console :as console]))

(describe "tictactoe-clojure.human-player"
          
  (with empty-board  [1 2 3 4 5 6 7 8 9])       
  (describe "#human-move"
    (it "returns the index of the move if the move is in a valid slot on an empty board"
      (with-redefs [console/prompt (fn [_] "1")]
        (should= 1 (human-move @empty-board))))
    (it "displays a message to let the user know the move is invalid and prompts the user to renter a move" 
      (should-contain "Invalid Input. Please try again." (with-in-str "12\n2\n" (with-out-str (human-move @empty-board)))))))
