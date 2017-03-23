(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.player :as player]
            [tictactoe-clojure.stringify-msg :as stringify-msg]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with x-win-board ["X" "X" "X" 4 "O" 6 7 8 "O"])
  (with x-1-move-from-win-board [1 "X" "X" 4 "O" 6 "X" "X" 9])
  (with human-x (player/create-player "Human" "X"))
  (with human-o (player/create-player "Human" "O"))
  (with players [@human-x @human-o])

  (describe "#play-game"
    (it "displays a message for who should move"
      (with-redefs [rules/game-over? (fn [_] true)
                    console/display (fn [_] "")]
        (should-invoke stringify-msg/whose-turn {:times 1} 
          (play-game @players @new-board))))
    (it "displays the board to the screen"
      (with-redefs [rules/game-over? (fn [_] true)
                    console/display (fn [_] "")]
        (should-invoke stringify-msg/transform-to-grid {:times 1} 
          (play-game @players @new-board))))
    (it "displays the board when the game starts and after each move"
          (should-invoke stringify-msg/transform-to-grid {:times 6}
            (with-in-str "1\n5\n2\n4\n3\n" (with-out-str 
              (play-game @players @new-board)))))
    (it "end the game loop and shows the result by calling game-result if the game is over"
      (with-redefs [rules/game-over? (fn [_] true)
                    console/display (fn [_] "")]
        (should-invoke stringify-msg/game-result {:times 1} 
          (play-game @players @x-win-board))))
    (it "continues the game until the game is over with a win"
      (should-contain "Player X win"
        (with-in-str "1\n5\n2\n4\n3\n" (with-out-str 
          (play-game @players @new-board)))))
    (it "continues the game until the game is over with a draw"
      (should-contain "Its a draw"
        (with-in-str "1\n5\n2\n3\n7\n4\n6\n9\n8\n" 
          (with-out-str (play-game @players @new-board)))))
    (it "asks both player X and O to move"
      (should-contain (and "Player X move" "Player O move")
        (with-in-str "1\n5\n2\n4\n3\n" (with-out-str 
          (play-game @players @new-board)))))))
