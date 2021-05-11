(ns api-validacao.routes.cpf
  (:require [api-validacao.controllers.cpf :as cpf]))


(def routes
  ["/cpf"
   {:swagger {:tags ["cpf"]}}
   ["/generate" {:get {:summary    "Generate X's valids CPFs"
                       :parameters {:query {:with_dots boolean?}}
                       :responses  {200 {:body {:cpf string?}}}
                       :handler    (fn [{{:keys [query]} :parameters}]
                                     (cpf/handle-cpf-generate query))}}]
   ])

