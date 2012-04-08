(ns favfetcher.views.root
  (:require [favfetcher.views.common :as common]
            [noir.content.getting-started]
            [noir.response :as response])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (common/layout
           [:p "Welcome to favfetcher"]))

(defpage [:post "/"] {:keys [url]}
         (if url
           (response/json {:url url})
           (response/json {:error "You must have a url parameter in your request"})))
