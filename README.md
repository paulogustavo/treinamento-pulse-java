[![Build Status](https://img.shields.io/badge/maven-0.1.0)](https://img.shields.io)
[![Build Status](http://192.168.6.95:32595/buildStatus/icon?job=engenharia-rest-treinamento-master)](http://192.168.6.95:32595/job/engenharia-rest-treinamento-master/job/master/)
[![Quality Gate](http://192.168.6.182:9000/api/badges/gate?key=engenharia-rest-treinamento_master)](http://192.168.6.182:9000/dashboard/index/engenharia-rest-treinamento_master)
[![Quality Gate](http://192.168.6.182:9000/api/badges/measure?key=engenharia-rest-treinamento_master&metric=coverage)](http://192.168.6.182:9000/dashboard/index/engenharia-rest-treinamento_master)

treinamento
==============

## Estoque Pulse

### Requisitos

* Java 1.8
* Docker 19.03.8
* Maven 3.5.4

### Subindo os containers

No diretório estoque, executar os comandos:

```
  mvn clean package
```
```
  docker-compose build
```
```
  docker-compose up
```

### Testes dos endpoints com o Swagger

Acesse o link: http://localhost:8081/swagger-ui.html