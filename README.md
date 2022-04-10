# Introdução
## O repositório
Este é o repositório de implementação do projeto prático da turma de Projeto e Implementação de Banco de Dados, ministrada durante o período de ENPE 4 (2021/2) pela professora Marilde Terezinha Prado Santos. 

## O projeto
O projeto consiste no desenvolvimento de um sistema digital para uma companhia de táxi, que conta com uma camada Web e uma camada de Banco de Dados.

# Pré-requisitos
- Docker
- Docker Compose
- Maven
- Java 11+

# Instruções
1. Clone o repositório para sua máquina local e entre no repositório.

    (com ssh)
    
    > git clone git@github.com:gsvboas/taxon && cd taxon
    
    ou (sem ssh)
    
    > git clone https://github,com/gsvboas/taxon && cd taxon

2. Crie uma package do sistema. É de extrema importância que isto seja feito *ANTES* de levantar o container, pois o container criará um volume associado à essa package.
    > mvn package
3. Suba os containers web e pg.
    > docker-compose up
4. Desenvolva! 

Para atualizar as mudanças realizadas no servidor, você precisa simplesmente atualizar a package do sistema (não é necessário a utilização de comandos de deploy).

    mvn package

Para acessar o artefato no servidor, basta abrir a seguinte url no seu navegador de preferência:

    localhost:8081/taxon

Caso seja necessário alterar a porta do servidor (i.e. se você já estiver com a porta 8082 ocupada), basta alterar a porta no docker-compose.yml
