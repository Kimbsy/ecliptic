(ns gear-design.gears
  (:require [gear-design.open-scad :as os]
            [clunk.util :as u]))

;; @TODO: calculate the radii based on the number of teeth, so that
;; the teeth and the gaps between them are the same size

(defn gear
  [n-teeth]
  (let [major-radius 10
        minor-radius 3.3
        recess-radius 0.46
        major-size [(* major-radius 2) (* major-radius 2)]
        major-offset [(- major-radius) (- major-radius)]
        teeth-positions (mapv (partial mapv + major-offset)
                              (u/ellipse-points major-size :num-points n-teeth))
        minor-size [minor-radius minor-radius]
        minor-offset [(- (/ minor-radius 2)) (- (/ minor-radius 2))]]

    ;; we care about the first and third outer circles
    (let [tooth (->> (for [[x y] [(first teeth-positions) (nth teeth-positions 2)]]
                       (os/translate {:x x :y y}
                                     (os/circle {:radius minor-radius})))
                     (apply os/intersection))
          ;; duplicate them around the circle
          teeth (apply os/union
                       (for [i (range n-teeth)]
                         (os/rotate {:z (* i (/ 360 n-teeth))}
                                    tooth)))

          ;; cut off the tips
          blunted (os/intersection
                   teeth
                   (os/circle {:radius (+ major-radius (* minor-radius 0.4))}))

          ;; add to a disc
          on-disc (os/union
                   blunted
                   (os/circle {:radius major-radius}))

          ;; cut out recesses
          recessed (os/difference
                    on-disc
                    (apply os/union
                           (for [[i [x y]] (zipmap (range)
                                                   teeth-positions)]
                             (os/rotate
                              {:z (/ 360 n-teeth 2)}
                              (os/translate {:x x :y y}
                                            (os/circle {:radius recess-radius}))))))

          ;; cut a hole in the middle
          holed (os/difference
                 recessed
                 (os/circle {:radius 3}))

          ;; give it some thickness
          extruded (os/linear-extrude {:height 2} holed)]
      extruded)))

(os/write (gear 25))
