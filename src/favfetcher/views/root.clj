(ns favfetcher.views.root
  (:require [favfetcher.views.common :as common]
            [noir.content.getting-started]
            [noir.response :as response]
            [net.cgrand.enlive-html :as html]
            [clj-http.client :as client])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (common/layout
           [:p "Welcome to favfetcher"]))

(defpage [:post "/"] {:keys [url]}
         (if url
           (if (= (val (find (client/get url {:throw-exceptions false}) :status)) 200)
             (response/json {:status 200 :message url})
             (response/json {:status 404 :message "Unreachable url"}))
           (response/json {:status 500 :message "You must have a url parameter in your request"})))

(defn fetch-url [url]
      (html/html-resource (java.net.URL. url)))

(defn favicon-at-root-domain [url]
      (client/get url))
