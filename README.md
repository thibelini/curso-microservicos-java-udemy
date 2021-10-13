# Curso Arquitetura Microserviços com Spring Cloud Netflix - UDEMY

Este projeto tem com finalidade desenvolver uma solução completa, usando Netflix Eureka Server (Service registry) onde serão registrados os nossos micros serviços,
NetFlix Zuul (Gateway) responsável por fazer o roteamento, Service Broker(RabbitMQ) para troca de mensagens entre micros serviços, Spring Boot, Spring Security, Spring Data, Java 11, MySql

## Tabelas no Mysql
- crud
- pagamento
- auth

## Como iniciar a aplicação
<!--ts-->
 * [Clonar o repositório ```git clone https://github.com/thibelini/curso-microservicos-java-udemy.git```](#repo)
 * [Subir aplicação do RabbitMQ](#docker)
    * [Executar o comando ```docker-compose up -d``` na pasta raiz do projeto](#docker)
    * [Apos subir, validar se esta funcionando em:](#local-files)
      * [url: ```http://localhost:15672```](#urlrabb)
      * [login: ```admin```](#login)
      * [senha: ```admin```](#senha)
      * Criar Queue: ```crud.produto.queue``` na Exchange ```crud.exchange``` com a RoutingKey ```crud.produto.routingKey```
 * [Subir os projetos na sequência abaixo:](#testes)
    * [1 - Discovery -> para que consigam se registrar](#Discovery)
      * [url: ```http://localhost:8761/registry/```](#disoverurl)
    * [2 - Gateway -> todas as requisições passam por ele (Porta de entrada)](#Discovery)
    * [3 - Auth -> serviço para solicitação do token para as requisições](#Discovery)
      * [url: GET -> ```http://localhost:8080/api/auth/login```](#urlauth)
      * raw -> json: 
        * ```json 
          {
              "userName": "administrador",
              "password": "admin"
          } 
          ```
       * Usar o token de retorno na Header nos demais serviços abaixo
    * [4 - Crud -> serviço para cadastrar os produtos](#Discovery)
      * Cadastro de Produto:  
        * [url: POST -> ```http://localhost:8080/api/crud/produto```](#crudpost)
        * Header usar o token pego no item 3
        * raw -> json: 
          * ```json 
            {
                "nome": "Ipad",
                "estoque": 2,
                "preco": 799.90
            }
            ```
        * Ao cadastrar um produto, automaticamente adiciona na fila para alimentar o serviço de Pagamento
    * 5 - Pagamento -> serviço responsável por gerar e venda:  
        * [url: POST -> ```http://localhost:8080/api/pagamento/venda```](#crudpost)
        * Header usar o token pego no item 3
        * raw -> json: 
          * ```json 
            "data": "2020-10-11",
            "valorTotal": 37.90,
            "produtos": [
                {
                    "idProduto": 3,
                    "quantidade": 1
                },
                {
                    "idProduto": 4,
                    "quantidade": 3
                }
            ]
            ```
    * 5 - Eurekas -> serviço responsável por gerar e venda e o pagamento:  
        * [url: POST -> ```http://localhost:8080/api/pagamento/venda```](#crudpost)
        * Header usar o token pego no item 3
        * raw -> json: 
          * ```json 
            "data": "2020-10-11",
            "valorTotal": 37.90,
            "produtos": [
                {
                    "idProduto": 3,
                    "quantidade": 1
                },
                {
                    "idProduto": 4,
                    "quantidade": 3
                }
            ]
            ```
<!--te-->
