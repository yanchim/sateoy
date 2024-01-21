(ns sateoy.state
  (:require
   [reagent.core :refer [atom]]))

(defonce show-chat? (atom false))
(defonce chat-name (atom ""))
(defonce chat-msg (atom ""))
(defonce chat-msg-list (atom []))
