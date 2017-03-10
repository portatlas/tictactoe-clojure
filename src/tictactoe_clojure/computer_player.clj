(ns tictactoe-clojure.computer-player
  (:require [tictactoe-clojure.board :as board])
  (:require [tictactoe-clojure.console :as console])
  (:require [tictactoe-clojure.rules :as rules]))

(defn- create-pair
  [coll-1 coll-2]
    (partition 2 (interleave coll-1 coll-2)))

(declare score-board)

(defn optimal-move-by-score 
  [player board depth maximum]
    (let [possible-moves (board/valid-slots board)
          score-of-possible-move (map #(score-board player (board/move board % player) depth maximum) possible-moves)
          move-score-pair (create-pair possible-moves score-of-possible-move)
          sorted-move-scores-pair (sort-by second move-score-pair)]
      (if maximum 
        (last sorted-move-scores-pair)
        (first sorted-move-scores-pair))))

(defn score-board 
  [player board depth maximum]
    (cond 
      (= (rules/draw? board) true) 0
  	  (= (rules/winner board) player) 
  	    (if maximum 
  	  	  (- 10 depth) 
  	  	  (+ -10 depth))
      (= (rules/winner board) (rules/whose-turn player)) 
        (if maximum 
      	  (+ -10 depth) 
      	  (- 10 depth))
      :else 
        (second 
          (optimal-move-by-score (rules/whose-turn player) board (dec depth) (not maximum)))))

(defn optimal-move 
  [board depth]
    (do
      (console/display "Computer's move:")
      (first 
        (optimal-move-by-score "X" board depth true))))