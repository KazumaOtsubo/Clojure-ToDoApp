(ns todo-clj.handler.user
    (:require [compojure.core :refer [defroutes context GET POST]]
              [todo-clj.db.user :as user]
              [todo-clj.util.response :as res]
              [todo-clj.view.user :as userview]
              [bouncer.validators :as v]
              [todo-clj.util.validation :as uv]))

(defn user-login  [{:as req :keys [params]}]
  (-> (userview/user-view req)
      res/ok
      res/html))

(defn user-login-post  [{:as req :keys [params]}]
;;   (uv/with-fallback #(todo-new (assoc req :errors %))
  ;; (let [params (uv/validate params todo-validator)]
    (if-let [result (user/verify-user (:name params) (:password params))]
      (if (true? (= result "SUCCESS"))
        (-> (res/found "/todo")
            (assoc :flash {:msg "ログインに成功しました"})
            res/html)
        (-> (res/found "/user/login")
            (assoc :flash {:msg "ログインに失敗しました"})
            res/html))))
          

(defn user-register  [{:as req :keys [params]}]
  (-> (userview/user-register-view req)
      res/ok
      res/html))

(defn user-register-post  [{:as req :keys [params]}]
  (if-let [result (first (user/create-user (:name params) (:password params)))]
    (-> (res/found "/todo")
        (assoc :flash {:msg (str (:name result) " を新規作成しました")})
        res/html)
    (res/internal-server-error!)))

(defroutes user-routes
  (context "/user" _
    (GET "/login" _ user-login)
    (POST "/login" _ user-login-post)
    (GET "/new" _ user-register)
    (POST "/new" _ user-register-post)))
  