(ns tictactoe-clojure.core
 (:require [tictactoe-clojure.console :as console])
 (:require [tictactoe-clojure.rules :as rules])
 (:require [tictactoe-clojure.board :as board])
 (:require [tictactoe-clojure.game-setup :as game-setup])
 (:require [tictactoe-clojure.game :as game]))

(defn -main
  []
    (console/display rules/instructions)
      (let [vs-type (game-setup/opponent-type)
            match (game-setup/matchup-opponents vs-type)
            board-size (game-setup/get-board-size)]
        (game/play-game match (board/new-board board-size))))
