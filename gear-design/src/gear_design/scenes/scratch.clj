(ns gear-design.scenes.scratch
  (:require [clunk.core :as c]
            [clunk.input :as i]
            [clunk.palette :as p]
            [clunk.shape :as shape]
            [clunk.util :as u]))

(def coral-pink (p/hex->rgba "#FF9B85"))

(defn sprites
  "The initial list of sprites for this scene"
  [{:keys [window] :as state}]
  [])

(defn draw-level-01!
  "Called each frame, draws the current scene to the screen"
  [{:keys [window current-scene] :as state}]
  (c/draw-background! coral-pink)
  (let [{:keys [major-radius minor-radius n-teeth]} (get-in state [:scenes current-scene :params])
        c (u/center window)
        major-size [major-radius major-radius]
        major-offset [(- (/ major-radius 2)) (- (/ major-radius 2))]
        major-pos (mapv + c major-offset)
        teeth-positions (mapv (partial mapv + major-pos)
                         (u/ellipse-points major-size :num-points n-teeth))
        minor-size [minor-radius minor-radius]
        minor-offset [(- (/ minor-radius 2)) (- (/ minor-radius 2))]]

    (shape/draw-ellipse! state major-pos major-size p/black :line-width 3)

    (doseq [p teeth-positions]
      (shape/draw-ellipse! state (mapv + p minor-offset) minor-size p/black :line-width 3))

    ))

(defn update-level-01
  "Called each frame, update the sprites in the current scene"
  [state]
  state)

(defn k
  [{:keys [current-scene] :as state} e]
  (cond
    (i/is e :key i/K_UP)
    (update-in state [:scenes current-scene :params :minor-radius] + 10)

    (i/is e :key i/K_DOWN)
    (update-in state [:scenes current-scene :params :minor-radius] - 10)

    (i/is e :key i/K_LEFT)
    (update-in state [:scenes current-scene :params :n-teeth] #(max 1 (dec %)))

    (i/is e :key i/K_RIGHT)
    (update-in state [:scenes current-scene :params :n-teeth] inc)

    :else
    state))

(defn init
  "Initialise this scene"
  [state]
  {:sprites (sprites state)
   :draw-fn draw-level-01!
   :update-fn update-level-01
   :key-fns [k]
   :params {:major-radius 400
            :minor-radius 100
            :n-teeth 10}})
