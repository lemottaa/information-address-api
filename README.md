# Information Address API

<!-- TABLE OF CONTENTS -->
## Conteúdo

* [Sobre o projeto](#sobre-o-projeto)
 * [O que foi utilizado](#o-que-foi-utilizado)
* [Começando com o projeto](#começando-com-o-projeto)
 * [Pré Requisitos](#pre-requisitos)
 * [Variaveis de ambiente](#variaveis-de-ambiente)
 * [Instalação](#instalação)
 * [Cobertura com SonarQube](#cobertura)
 * [Serviços disponíveis](#serviços)
* [Documentação](#documentação)
* [Contato](#contato)
 
<!-- ABOUT THE PROJECT -->
## Sobre o projeto
 
O projeto information-address-api é responsável por retornar o endereço brasileiro de qualquer CEP válido informado.
 
A linguagem escolhida foi o Java, já que se consolidou por muitos anos no mercado de linguagem de programação, é de código aberto e tem uma comunidade grande e ativa. Além disso, tem frameworks que são facilitadores no processo de criação de apis, Spring e Spring Boot, como neste projeto.

### O que foi utilizado

[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Linguagem de programação utilizada como citado anteriormente
 
[Maven](https://maven.apache.org/) - Ferramenta de automação de compilação
 
[Spring Boot](https://spring.io/projects/spring-boot) - Framework que visa facilitar o processo de configuração e publicação de aplicações que utilizem o ecossistema Spring
 
[JWT](https://jwt.io/) - Permite decodificar, verificar e gerar tokens
 
[JUnit5](https://junit.org/junit5/docs/current/user-guide/) - Possibilita uma base atualizada para testes do lado do desenvolvedor
 
[JaCoCo](https://github.com/jacoco/jacoco) - Plugin utilizado para coverage da aplicação.
 
[CircleCI 2](https://circleci.com/) - Utilizado no CI (Continuos Integration) da aplicação.
 
[Swagger](https://swagger.io/tools/swagger-editor/) - Fornece interface gráfica para os recursos da api, bem como uma documentação simplificada e objetiva.
 
[Sleuth](https://spring.io/projects/spring-cloud-sleuth) - Possibilita o acompanhamento de trace de logs distribuídos
 
[LogStash](https://www.elastic.co/pt/logstash) - Pipeline de processamento de dados (logs) e integração com ferramentas de processamento de dados
 
[ElasticSearch](https://www.elastic.co/pt/what-is/elasticsearch) - Mecanismo que possibilita e facilita a análise de dados textuais
 
[Kibana](https://www.elastic.co/pt/kibana) - Aplicação que fornece recursos de busca e visualização de dados indexados no Elasticsearch.
 
[Sonar](https://docs.sonarqube.org/latest/) - Ferramenta que visa garantir a qualidade do código fonte em desenvolvimento.

## Começando com o projeto

### Pré Requisitos

```sh
Java 11
Maven
Docker
Spring Tools for Eclipse (STS4) ou IntelliJ
Lombok
```

### Variáveis de ambiente
***

TOKEN
```sh
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIiwiaXNzIjoiaW5mb3JtYXRpb24tYWRkcmVzLWFwaSIsImlhdCI6MTU4NDEyNDY5M30.PtGT8gYoh4ypR8hxBc-detd2ukd0tPf0KJi7JUBdGxE
```
***

| VARIÁVEL                       | DESCRIÇÃO         |
| ------------------------------ | :---------------: |
| ENV | Profile de execução desejado |
| JWT_PREFIX | Prefixo do header de autenticação desejado |
| JWT_SECRET | Secret criado para validação do token |
| API_TIMEOUT | Tempo máximo de execução de read/write do Feign |
| FEIGN_LOGGER_LEVEL | Nivel de logs do FEign |
| CEP_API_URL | URL da api com a base de CEP do Brasil |
| CACHE_TIME_EXPIRE_IN_MINUTES | Quantidade em minutos de experição do cache |
***
### Instalação

Acessar a pasta raiz do projeto:

**Compilar o projeto:**

```sh
sdk use java 11.0.2-open
./mvnw clean package
```

**Executar o coverage:**

```sh
sdk use java 11.0.2-open
./mvnw clean install jacoco:report
```

**Executar o projeto com configuração local:**

```sh
sdk use java 11.0.2-open
sudo docker-compose up -d
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=hml
```
***
### Cobertura com SonarQube

1) Com o dokcer-compose em execução, estará disponível o [Dashboard do sonar](http://localhost:9000). Desta forma, basta realizar o login com as credenciais: login - admin | senha - admin;
2) Agora se faz necessário criar a aplicação que será analisada pelo sonar. O nome e o token da aplicação serão solicitados, e minha sugestão é que use o mesmo para os dois;
3) Após continuar, como o projeto é Java rodando com Maven, escolha as duas opções sucessivamente;
4) Feito o passo anterior, surgirá um comando maven (vide abaixo) na tela. Este deve ser copiado;
```sh
./mvnw sonar:sonar \
 -Dsonar.host.url=http://localhost:9000 \
 -Dsonar.login=9289f191275ad5aa535345b8poj28d133c7784h
```
5) Execute a compilação novamente do projeto através do comando:
```sh
./mvnw clean package
```
6) Está pronto. O projeto estará disponível no [Dashboard do sonar](http://localhost:9000).

### Serviços Disponiveis

| Serviço                 | Portas                 |
|-------------------------|------------------------|
|Zipkin                   | 9411                   |
|ElasticSearch            | 9200, 9300             |
|Information Address API  | 9002                   |
|SonarQube                | 9000                   |
|Spring Boot Admin Server | 9002                   |
|LogStash                 | 5000, 5044, 8080, 9600 | 
|Kibana                   | 5601                   |

## Documentação

Link do documento de design da arquitetura utilizada:
https://drive.google.com/file/d/1PBNLdJWpK69F3LCx0DkfMINDip-htZxO/view?usp=sharing

Documentação de endpoints: http://localhost:9002/information-address/swagger-ui.html#/

## Contato
[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/lmotta18/)](https://www.linkedin.com/in/lmotta18/)
![Skype Badge](https://img.shields.io/badge/-motta1840-lightblue?style=flat-square&logo=Skype&logoColor=white)
[![Gmail Badge](https://img.shields.io/badge/-leonardo.motta@luizalabs.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardo.motta@luizalabs.com)](leonardo.motta@luizalabs.com)