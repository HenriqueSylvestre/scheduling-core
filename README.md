<h1 align="center">Plataforma de comunicação - 1° Sprint</h1>
<p align="center">Microserviço para realizar o agendamento de envio de comunicação dos seguintes tipos: email, sms, push notification e WhatsApp</p>

### Sobre

Cenário
O Magalu tem o desafio de desenvolver uma plataforma de comunicação. Você foi escolhido(a)
para iniciar o desenvolvimento da primeira sprint.

Requisitos

● Deve ter um endpoint que receba uma solicitação de agendamento de envio de
comunicação (1);
○ Este endpoint precisa ter no mínimo os seguintes campos:
■ Data/Hora para o envio
■ Destinatário
■ Mensagem a ser entregue

○ As possíveis comunicações que podem ser enviadas são: email, sms, push e
whatsapp;

○ Neste momento, precisamos deste canal de entrada para realizar o envio, ou
seja, esse endpoint (1). O envio em si não será desenvolvido nesta etapa: você
não precisa se preocupar com isso;

○ Para esta sprint ficou decidido que a solicitação do agendamento do envio da
comunicação será salva no banco de dados. Portanto, assim que receber a
solicitação do agendamento do envio (1), ela deverá ser salva no banco de
dados;

○ Pense com carinho nessa estrutura do banco. Apesar de não ser você quem vai
realizar o envio, a estrutura já precisa estar pronta para que o seu coleguinha
não precise alterar nada quando for desenvolver esta funcionalidade. A
preocupação no momento do envio será de enviar e alterar o status do registro
no banco de dados.

● Deve ter um endpoint para consultar o status do agendamento de envio de
comunicação (2). O agendamento será feito no endpoint (1) e a consulta será feita por
este outro endpoint.

● Deve ter um endpoint para remover um agendamento de envio de comunicação.

### Features

- Agendamento de envio de comunicação
- Consulta de agendamento de envio de comunicação
- Exclusão de agendamento de envio de comunicação
- Cadastro de clientes
- Consulta de clientes
- Exclusão de clientes
- Cadastro de tipo de status de agendamento
- Consulta de tipo de statos de agendamento 
- Exclusão de tipo de statos de agendamento

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Java 14](https://www.java.com/pt-BR/), [Docker](https://www.docker.com/), [Docker Compose](https://docs.docker.com/compose/install/) e imagem do mySql no Docker.

### 🎲 Rodando a aplicação

```bash
# Clone este repositório
$ git clone https://github.com/HenriqueSylvestre/scheduling-core.git

# Acesse a pasta do projeto no terminal/cmd
$ cd scheduling-core

# Suba o container docker do mySql utilizando as configurações descrita no docker-compose.yml executando o comando
$ docker-compose up -d

# Consulte os container do docker em execução
$ docker ps

#Execute a aplicação da forma que desejar. Como por exemplo: usando uma IDE de desenvolvimento como IntelliJ IDEA Community

# O servidor iniciará na porta:8080 - acesse <http://localhost:8080>
```

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java 14](https://www.java.com/pt-BR/)
- [Spring boot](https://spring.io/projects/spring-boot)
- [MySql](https://www.mysql.com/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/)
- [Tomcat](http://tomcat.apache.org/)
- [Junit5](https://junit.org/junit5/)
- [RestAssured](https://rest-assured.io/)

### Autor

Henrique Sylvestre da Silva 

Email: henriquesylvestresilva@gmail.com 

Linkedin: https://www.linkedin.com/in/henrique-sylvestre-da-silva-216b30156
