(ns todo-clj.view.main
    (:require [todo-clj.view.layout :as layout]))
  
    (defn home-view [req]
        (->> [:section.card
              [:h2 {:style "text-align:center"}"ToDoアプリ"] 
            ;;   [:a {:href "/todo"} "TODO 一覧"]
              [:section.card
               [:p "Clojureを利用してToDoアプリを作成しました。"]
               [:p "Clojureには、過去学んできた言語にはない記法がいくつもあり、その習得に時間がかかりました。"]
               [:p "\"関数型言語\"というのも始めて学ぶ形式でしたので、考え方の切り替えが必要でした。"]
               [:p "しかし、序の口も序の口ですが、Clojureの面白さも少し分かった気がしています。"]
               [:p "REPLを用いた開発は、非常にスピーディーに結果を確認できますし、\"->\" はとっつきにくかったですが、便利だと感じます"]
               [:br]
               [:p "開発にあたり、理解を確認するために、ログイン機能も追加してみました。"]
               [:p "ユーザー名とパスワードの整合性を確認するだけの、簡単な機能です。"]
               [:p "ToDoは全ユーザー共通なので、あまり意味はないです。"]
               [:br]]
              [:br]
              [:a {:href "/user/login"} "ログイン画面"]]
             (layout/common req)))