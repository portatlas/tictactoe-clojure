(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with first-player #tictactoe_clojure.player.Human{:piece "X"} )
  (with second-player #tictactoe_clojure.player.Human{:piece "O"})
  (with players [@first-player @second-player])

  (describe "#play-game"
    (it "loops through a game where the human enters 1, 2, 4 and loses to the computer"
      (with-redefs [console/display (fn [_] "Player O wins")]
      	(with-out-str (with-in-str "1\n4\n2\n5\n3\n"
          (should= "Player O wins" (play-game @players @new-board))))))))
