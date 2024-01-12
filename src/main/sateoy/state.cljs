(ns sateoy.state
  (:require
   [reagent.core :refer [atom]]))

(defonce chat (atom {:show false}))
