# xy-INC
Gerenciamento de POI (Pontos de Interesse)

Aplicação feita com Spring Boot, Spring Data com Banco Postgress, Rest com swagger.

<h1>Subindo Aplicação Completa</h1>

Para rodar a aplicação deve-se criar um banco Postgress(Usuario:posrgres Senha:admin)

Criar um database com nome poigps

rodar o comando

```
mvn spring-boot:run
```

O flyWay irá criar as tabelas necessárias e aplicação estará com os endpoints disponíveis em :

```
http://localhost:8080/swagger-ui.html
```

<h1>Testes com Mokito</h1>

Para rodar a classe de testes configurada com as conexão de banco mokadas (Mokito)

Deve-se executar o seguinte comando:

```
mvn clean test -Dtest=br.com.gwdev.poiGPS.PoiGPSApplicationTests
```

Com isso os testes dos serviços irão rodar sem necessidade de conexão com o banco.

<h1>Testes de Integração</h1>

Deve-se executar o seguinte comando:

```
mvn clean test -Dtest=br.com.gwdev.poiGPS.PoiGPSIntegrationTests
```

O flyWay irá criar as tabelas necessárias em um banco H2 local e configurar os registros básicos para os testes