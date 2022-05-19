(ns todo-clj.db.user
    (:require [clojure.java.jdbc :as jdbc]
              [todo-clj.db :as db]))
  
  (defn create-user [name password]
    (jdbc/insert! db/db-spec :user_table {:name name :password password}))
  
  (defn verify-user [name password]
    (if (true? (= password 
                  (:password (first 
                              (jdbc/query db/db-spec ["select password from user_table where name = ?" name])))))
      "SUCCESS"
      "false"))
