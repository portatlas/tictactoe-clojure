(ns tictactoe-clojure.console)

(defn display 
  [message]
    (println message))

(defn prompt 
  [message]
    (println message)
    (read-line))

(defn show-board
  [board]
    (display (str (nth board 0) " | " (nth board 1) " | " (nth board 2)))
    (display (str "---------"))
    (display (str (nth board 3) " | " (nth board 4) " | " (nth board 5)))
    (display (str "---------"))
    (display (str (nth board 6) " | " (nth board 7) " | " (nth board 8))))
 