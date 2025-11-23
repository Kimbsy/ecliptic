(ns gear-design.core
  (:gen-class)
  (:require [clunk.core :as c]
            [gear-design.scenes.scratch :as scratch]))

(defn init-scenes
  "Map of scenes in the game"
  [state]
  {:scratch (scratch/init state)})

;; Configure the game
(def gear-design-game
  (c/game {:title "gear-design"
           :size [800 600]
           :init-scenes-fn init-scenes
           :current-scene :scratch
           :assets {}}))

(defn -main
  "Run the game"
  [& args]
  (c/start! gear-design-game))
