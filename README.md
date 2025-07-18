# Livro na Lista: Sua Lista de Desejos de Livros

---

## Descrição

**Livro na Lista** é uma aplicação Java intuitiva que permite aos usuários gerenciar sua lista de livros desejados. Perfeito para quem adora ler e quer manter o controle de futuras aquisições!

---

## Como Configurar e Rodar

### Pré-requisitos

Certifique-se de ter o **Java 17** instalado em sua máquina.

### Configuração do Banco de Dados H2

Para que a aplicação funcione corretamente, você precisa configurar o acesso ao banco de dados H2.

1.  Navegue até o arquivo: `src/main/resources/application.properties`
2.  Edite as propriedades de conexão conforme necessário. O H2 é um banco de dados em memória, então geralmente a configuração padrão já funciona para desenvolvimento.
3.  Para inspecionar o banco de dados e suas propriedades, você pode acessar o **console do H2** em: `http://localhost:8080/h2-console`

---

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**: Framework robusto para o desenvolvimento de aplicações Java.
    * **Spring Web**: Para a construção de APIs web e gerenciamento de requisições HTTP.
    * **Spring Data JPA**: Simplifica o acesso a dados, utilizando o Hibernate como provedor JPA.
    * **H2 Database**: Banco de dados relacional em memória, ideal para desenvolvimento e testes.
* **Thymeleaf**: Mecanismo de template server-side para a criação de interfaces de usuário dinâmicas.
* **Swagger UI**: Para a documentação interativa da API.

---

## Executando a Aplicação

1.  Clone o repositório para sua máquina local.
2.  Abra o projeto em sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code, etc.).
3.  Execute a classe principal da aplicação (geralmente `SuaAplicacaoApplication.java` se você usou o Spring Initializr, ou o nome que você deu ao seu arquivo principal).
4.  Acesse a aplicação no seu navegador:
    * **Frontend (Thymeleaf)**: `http://localhost:8080/livros/ui/listar`
    * **Documentação da API (Swagger UI)**: `http://localhost:8080/swagger-ui.html` (ou `http://localhost:8080/swagger-ui/index.html` dependendo da versão e configuração)

---
