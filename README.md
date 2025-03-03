<p align="center">
  <img src="https://github.com/user-attachments/assets/ba81d7b3-feb6-4c83-9d01-3fb44f170dc1" alt="Logo-PrimeOS">
</p>

<h3 align="center">PrimeOS 🚀</h3>
<h3 align="center">API RESTful para ordem de serviço!</h3>
<h5 align="center">(Expansões futuramente!)</h5>
<p align="center">
  <a href="https://java.com">
    <img src="https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white" alt="Java"></a>
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/Spring_Boot-3.4.3-brightgreen?logo=spring-boot" alt="Spring Boot"></a>
  <a href="https://spring.io/projects/spring-security">
    <img src="https://img.shields.io/badge/Spring_Security-6.x-brightgreen?logo=spring-security&logoColor=white" alt="Spring Security"></a>
  <a href="https://spring.io/projects/spring-data-jpa">
    <img src="https://img.shields.io/badge/Spring_Data_JPA-3.x-brightgreen?logo=spring&logoColor=white" alt="Spring Data JPA"></a>
  <a href="https://swagger.io/">
    <img src="https://img.shields.io/badge/Swagger_OpenAPI-2.8.5-blue?logo=swagger" alt="Swagger OpenAPI"></a>
  <a href="https://maven.apache.org/">
    <img src="https://img.shields.io/badge/Maven-3.x-red?logo=apache-maven" alt="Maven"></a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/MySQL-8-blue?logo=mysql&logoColor=white" alt="MySQL"></a>
</p>

---

<br>

<h2>📌 Visão Geral</h2>

**PrimeOS** é uma API RESTful desenvolvida como uma solução empresarial que oferece funcionalidades robustas para o gerenciamento de usuários, clientes e serviços, além de implementar recursos de segurança avançados, como autenticação JWT e criptografia de senhas, garantindo um ambiente confiável para o controle e monitoramento de ordens de serviço em tempo real.

🔎 Quer saber mais sobre os **detalhes do projeto, endPoints e data base**?  
Acesse o documento completo [aqui!](https://economic-jaborosa-ec9.notion.site/API-RESTful-PRIME-OS-1a8268a7953a80249eb9d47cbac28916?pvs=4)

<br>

<h2>✅ Principais Funcionalidades</h2>

#### 👥 Gestão de Funcionários
- Cadastro de funcionários com diferentes níveis de acesso
- CRUD completo para gestão de colaboradores
- Senhas criptografadas com BCrypt

#### 🏢 Gestão de Clientes
- Registro completo de informações de clientes
- Histórico de serviços associados
- Busca avançada por diferentes critérios

#### 🔧 Gestão de Serviços
- Cadastro de serviços com detalhamento técnico
- Associação a clientes e técnicos
- Consulta de serviços existentes
 
  <br>

<h2 align="center">🛠️ Tecnologias Utilizadas 🛠️</h2>

<br>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-3-%236DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-%236DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Security">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-%236DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/Swagger%20OpenAPI-3-%2385EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger">
  <img src="https://img.shields.io/badge/Hibernate%20Validator-%23007ACC?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate Validator">
  <img src="https://img.shields.io/badge/Maven-%23C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/MySQL-8-%234479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</p>
<br>


<h2 align="center">🌐 Arquitetura do Sistema 🌐</h2>

```mermaid
graph TD
    A[Usuário] -->|HTTP Requests| B[Security Filter]
    B -->|Request Autorizado| C[Controller]
    C -->|Chama| D[Service Layer]
    D -->|Valida Regras| E[DTOs/Validação]
    D -->|Acessa Dados| F[Repository]
    F -->|ORM| G[(MySQL)]
    D -->|Exception Handling| H[Global Exception Handler]
    C -->|Resposta| I[JSON Response]
    B -->|Request Não Autorizado| J[403 Forbidden]
    
    style A fill:#4CAF50,stroke:#388E3C
    style B fill:#F44336,stroke:#D32F2F
    style C fill:#2196F3,stroke:#1976D2
    style D fill:#FFC107,stroke:#FFA000
    style F fill:#9C27B0,stroke:#7B1FA2
    style G fill:#009688,stroke:#00796B
```
<br>

## 📚 Documentação da API

Explore todos os endpoints interativamente através do Swagger UI:  
`http://localhost:8080/swagger-ui.html`

**Exemplo de Requisição:**
```http
POST /auth/login
Content-Type: application/json

{
  "login": "admim",
  "senha": "admin"
}
```

**Resposta de Sucesso:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
}
```
<br>

## 📦 Faça a Instalação Local

### Pré-requisitos
- Java 17 JDK  
- MySQL 8+  
- Maven 4.0

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/gustavopecanhaa/PrimeOS-APIRestFul.git
   cd PrimeOS-APIRestFul
   ```

2. **Configure o banco de dados**
   ```sql
   CREATE DATABASE db_primeos;
   ```

3. **Configure as credenciais**
   ```properties
   # application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_primeos
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

Acesse a API:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **Endpoint base:** `http://localhost:8080/auth/login`

<br>

## 📄 Licença

Distribuído sob a licença MIT. Veja [LICENSE](./LICENSE) para mais informações.

<br>

## ✉️ Contato
**Fale com Gustavo Peçanha:** 

[![Gmail](https://img.shields.io/badge/Gmail-D14836?logo=gmail&logoColor=white)](mailto:gustavopecanhaa@outlook.com)  [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/gustavopecanhaa/)

Feito com muito carinho =D!
