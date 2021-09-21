# todolistapi
RESTFUL API simples em Java que armazena e atualiza tarefas

## Arquitetura
 - Foi utilizado a padrão hexagonal de arquitetura - ports & adapters (https://alistair.cockburn.us/hexagonal-architecture/)

## Setup
Para executar esse projeto as seguintes ferramentas foram utilizadas: 
 - Instalar o Java 8 (https://www.java.com/pt-BR/download/manual.jsp) e adicionar ao PATH
 - Instalar o Maven 3.X (https://maven.apache.org/download.cgi) e adicionar ao PATH
 - GIT (https://git-scm.com/downloads)
 - VsCode com os plugins (Extensin Pack for Java, Spring Initializr Java Support, Git Lens, XML Tools)
 - DynamoDB (https://docs.aws.amazon.com/pt_br/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
 - NoSQL Workbench (https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/workbench.settingup.html)
 - Postman (https://www.postman.com/downloads/)
 - Docker (https://docs.docker.com/get-docker/)
 - Outros: https://jwt.io/, jeager tracing, jacoco, etc

## Execução local
- Após instalar todos os itens do Setup devemos: 
    - Executar o DynamoDB: ' java -D"java.library.path=./DynamoDBLocal_lib" -jar DynamoDBLocal.jar -sharedDb ' on path é a pasta onde os arquivos do Dynamo foram descompactados.
    - Executar o jeager para habilitar o tracing da aplicação: 
    ```
        docker run -d --name jaeger \
            -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
            -p 5775:5775/udp \
            -p 6831:6831/udp \
            -p 6832:6832/udp \
            -p 5778:5778 \
            -p 16686:16686 \
            -p 14268:14268 \
            -p 14250:14250 \
            -p 9411:9411 \
            jaegertracing/all-in-one:latest
    ```
    - Acessar via terminal o diretório raiz da aplicação (onde está o pom.xml).
    - Compilar a aplicação: ' mvn package -Dmaven.test.skip '
    - Rodar a aplicação: ' java -jar target/todolistapi-1.0.0.jar '
    - Testar o funcionamento com o CURL: 
    ```
        curl --location --request POST 'localhost:8080/token' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --data-urlencode 'user=admin' \
        --data-urlencode 'pass=admin'
    ```

## Execução dos testes
- Existem dois tipos de testes no projeto: unitários e de componente.
- Para executá-los basta: ' mvn clean verify '
    - PS: O DynamoDB precisa estar executando localmente para o correto funcionamento do testes de componente.
- Após a execução, teremos o relatório do jacoco na pasta ' /target/site/jacoco/index.htm '