(ns tictactoe-clojure.computer-player
  (:use clojure.set)
  (:require [tictactoe-clojure.console :as console])
  (:require [tictactoe-clojure.board :as board])
  (:require [tictactoe-clojure.rules :as rules]))

(defn- create-pair
  [coll-1 coll-2]
    (partition 2 (interleave coll-1 coll-2)))

(declare optimal-move-by-score)
(declare score-board)

(def preferred-positions-on-4x4 #{1 4 13 16})
(def preferred-positions-on-5x5 #{13 1 5 21 25})

(defn- score-board 
  [player board depth maximum]
    (let [winner (rules/winner board)]
      (cond 
        (= (rules/draw? board) true) 0
        (= winner player) 
          (if maximum 
            (- 10 depth) 
            (+ -10 depth))
        (= winner (rules/switch-turn player)) 
          (if maximum 
            (+ -10 depth) 
            (- 10 depth))
        :else 
          (second 
            (optimal-move-by-score (rules/switch-turn player) board (dec depth) (not maximum))))))

(def memo-score-board (memoize score-board))

(defn- optimal-move-by-score 
  [player board depth maximum]
    (let [possible-moves (board/valid-slots board)
          score-of-possible-move (map #(memo-score-board player (board/move board % player) depth maximum) possible-moves)
          move-score-pair (create-pair possible-moves score-of-possible-move)
          sorted-move-scores-pair (sort-by second move-score-pair)]
      (if maximum 
        (last sorted-move-scores-pair)
        (first sorted-move-scores-pair))))

(defn- check-seq-for-dups [seq]
  (for [[id freq] (frequencies seq) 
        :when (> freq 1)]            
   id)) 

(defn- index-of-win-combo-with-potential-winner
  [board player]
    (.indexOf 
      (map #(check-seq-for-dups %) (rules/winning-combinations board)) 
        (list (rules/switch-turn player))))

(defn- board-with-potential-winner
  [board player]
    (let [sec-board-w-dup (index-of-win-combo-with-potential-winner board player)]
        (if (not= sec-board-w-dup -1) 
          sec-board-w-dup 
          nil)))    

(defn- opponent-close-to-win?
  [board player]
    (if (not= nil (board-with-potential-winner board player))
      true
      false))

(defn- move-on-preferred-position
  [board valued-moves]
    (first 
      (clojure.set/intersection 
        (set (board/valid-slots board)) 
        valued-moves)))

(defn- move-to-block-opponent
  [board player]
    (first 
      (board/valid-slots 
        (nth (rules/winning-combinations board) (board-with-potential-winner board player)))))

(defn optimal-move 
  [player board depth]
    (let [board4x4-with-12-open-spots (and (= (board/board-size board) 4) (> (count (board/valid-slots board)) 13) )
          board5x5-with-20-open-spots (and (= (board/board-size board) 5) (> (count (board/valid-slots board)) 20) (not= nil board-with-potential-winner board player))
          board5x5-with-12-open-spots (and (= (board/board-size board) 5) (> (count (board/valid-slots board)) 13) (opponent-close-to-win? board player)) ]
    (cond
      board4x4-with-12-open-spots
        (move-on-preferred-position board preferred-positions-on-4x4)
      board5x5-with-20-open-spots       
        (move-on-preferred-position board preferred-positions-on-5x5)
      board5x5-with-12-open-spots
        (move-to-block-opponent board player)
    :else 
      (first 
        (optimal-move-by-score player board depth true)))))
