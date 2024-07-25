# TESTE TOTVS

## VERSÕES
 - Java 17
 - Ubuntu 22.04
 - Docker version 27.0.3, build 7d4bcd8

## ANOTAÇÕES
 - A coleção postman está em formato JSON na pasta raiz do projeto
 - O CSV de exemplo para importação das contas está dentro da pasta resources
 - Para subir o projeto é necessário digitar docker-compose up
 - Endpoints que tem no caminho "ws-api" são autenticados e para isto precisam de um token Bearer para consumir

## ENDPOINTS
 - Existem 9 ENDPOINTS, o modelo do body deve ser seguido como está na coleção
 - LOGIN - POST
   - gerar o token JWT
 - CADASTRAR - POST
   - criar uma conta para depois consumir o endpoint de login
 - Atualizar conta - PUT
   - Serve para atualizar qualquer dado da conta
 - Atualizar situação da conta - PATCH
   - Serve para apenas atualizar a situação da conta que são:
     - ABERTA,
       VENCIDA,
       CANCELADA,
       PAGA
   - Ela contém uma regra que caso seja enviado como PAGA ele irá atualizar a data de pagamento
 - Cadastrar conta - POST
   - Serve para cadastrar uma conta, seguir o body na coleção
 - Carregar conta por ID - GET
   - Serve para trazer uma conta do usuario logado passando o ID como PATH PARAM
 - Carregar valor pago - GET
   - Serve para trazer o valor que o usuario ja pagou, por periodo olhando a data de pagamento
 - Carregar contas - GET
   - Serve para carregar uma lista de contas paginas
     - data-vencimento-inicial - Data inicial
     - data-vencimento-final - Data final
     - descricao - A descricao da conta com LIKE no começo e final (%EXEMPLO_DESCRICAO%)
     - pagina - A pagina que vai carregar
     - maximo-por-pagina - A quantidade por pagina

## CONTATO
 Qualquer dúvida/problema sigo a disposição.
 - caiosimas.siloe@gmail.com
 - (47) 99177-2862
 - https://www.linkedin.com/in/caio-simas/
 