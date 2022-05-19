(ns todo-clj.view.user
  (:require [hiccup.form :as hf] 
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [todo-clj.view.layout :as layout]))
  
  
(defn error-messages [req]
  (when-let [errors (:errors req)]
    [:ul
     (for [[k v] errors
           msg v]
       [:li.error-message msg])]))

  (defn user-view [req]
      (->> [:section.card
            (when-let [{:keys [msg]} (:flash req)]
              [:div.alert.alert-warning [:strong msg]])
            [:h2 {:style "text-align:center"} "ログイン"]
            (hf/form-to
             [:post "/user/login"]
             (anti-forgery-field)
             (error-messages req)
             [:div {:style "text-align:center"}
              [:input {:name :name :placeholder "ユーザー名"}]
              [:br]
              [:input {:name :password :placeholder "パスワード"}]
              [:br]
              [:button.bg-blue "ログイン"]])
            [:br]
            [:a {:href "/user/new"} "新規登録"]
            [:p "ユーザー名："[:b "user"] "　パスワード："[:b "pass"]"　でログインできます。"]]
       (layout/common req)))

    

  (defn user-register-view [req]
    (->>
     [:section.card
      [:h2 {:style "text-align:center"} "新規登録"]
      (hf/form-to
       [:post "/user/new"]
       (anti-forgery-field)
       (error-messages req)
       [:div {:style "text-align:center"}
        [:input {:name :name :placeholder "新しいユーザー名"}]
        [:br]
        [:input {:name :password :placeholder "新しいパスワード"}]
        [:br]
        [:button.bg-blue "登録"]])]
     (layout/common req)))