# Desafio de Desenvolvimento - Java PJ

Aplicação web desenvolvida com **Java** e **Spring Boot** para avaliar as habilidades de desenvolvimento.
O projeto foi desenvolvido para fins acadêmicos e utiliza banco de dados em memória para facilitar a execução.

---

# Tecnologias Utilizadas

* **Java:** 1.8
* **Spring Boot:** 2.5.x
* **Maven:** gerenciamento de dependências e build
* **Thymeleaf:** engine de templates para as páginas HTML
* **HSQLDB:** banco de dados em memória

---

# Banco de Dados

A aplicação utiliza **HSQLDB em memória**, o que significa que:

* o banco de dados é criado automaticamente ao iniciar a aplicação
* não é necessário instalar nenhum banco externo
* os dados são apagados quando a aplicação é encerrada

Isso permite que o sistema seja executado facilmente em qualquer ambiente.

---

# Como Executar a Aplicação

## 1. Pré-requisitos

Antes de executar o projeto, instale:

* **Java JDK 8+**
* **Maven 2.5 ou superior**
* **Git**

---

## 2. Clonar o repositório

Abra o terminal e execute:

```
git clone https://github.com/srfssa/desafioDX-PJ.git
```

Acesse a pasta do projeto:

```
cd SEU_REPOSITORIO
```

---

## 3. Compilar o projeto

Execute o comando abaixo para baixar as dependências e compilar o projeto:

```
mvn clean install
```

---

## 4. Executar a aplicação

Para iniciar a aplicação execute:

```
mvn spring-boot:run
```

Outra opção é executar diretamente pela IDE a classe principal do projeto que contém a anotação:

```
@SpringBootApplication
```

---

## 5. Acessar a aplicação

Após iniciar o projeto, abra o navegador e acesse:

```
http://localhost:8082
```

---

# Estrutura do Projeto

```
src
 └── main
     ├── java
     │    ├── config
     │    ├── controller
     │    ├── entity
     │    ├── mapper
     │    ├── model
     │    ├── repository
     │    ├── service
     │    └── util
     │
     └── resources
          ├── templates
          ├── static
          └── application.properties
```

---

# Observações

* O banco **HSQLDB é reiniciado sempre que a aplicação é encerrada**.
* existe dados iniciais no projeto (carga automática); serão recriados a cada execução.

---

# Autor
```
Saulo Farias
```
Projeto desenvolvido para fins de avalidação de desempenho.
