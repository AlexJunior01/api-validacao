(ns api-validacao.routes.cpf
  (:require [api-validacao.controllers.cpf :as cpf]
            [api_validacao.http :as http]))

(def routes
  ["/cpf"
   {:swagger {:tags ["cpf"]}}
   ["/generate" {:get {:summary    "Generate one valid CPF"
                       :parameters {:query {:with_dots boolean?}}
                       :handler    (fn [{{:keys [query]} :parameters}]
                                     (cpf/handle-cpf-generate query))}}]
   ])

