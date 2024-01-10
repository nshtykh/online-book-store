# <p align="center"><img src="https://i.pinimg.com/originals/b4/13/34/b41334a036d6796c281a6e5cbb36e4b5.gif" width="40"/>ONLINE BOOK STORE<img src="https://i.pinimg.com/originals/b4/13/34/b41334a036d6796c281a6e5cbb36e4b5.gif" width="40"/></p>
This project is the implementation of a web application for the Online Bookstore using Java and Spring Boot. The goal of the project was to create a user-friendly interface for browsing and ordering books. 📚

The project allows users to find, browse, and order books, as well as track the history of their orders. 🕵️ For administrators, I implemented book catalog management, adding and deleting books, updating categories, and tracking orders. 🛒

The online bookstore enables users to enjoy their purchases and administrators to manage the store efficiently. 🚀

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
### <img src="https://media2.giphy.com/media/CNjoBV8X6bA6kC1hmw/giphy.gif?cid=6c09b952o0m8p765cuilvts077hjtwc54yaqj2loypwf728l&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s" width="40"/>Technologies and tools:

<details>
  <summary><b>Spring Boot</b></summary>

*A framework for building and deploying Java applications with an embedded server, simplifying configuration and accelerating development.*
</details>
<details>
  <summary><b>Spring Data JPA</b></summary>

*Part of the Spring Data project, providing an abstraction for working with databases through JPA (Java Persistence API), simplifying interaction with relational databases.*
</details>
<details>
  <summary><b>Spring MVC</b></summary>

*Model-View-Controller framework for developing web applications, enabling easy creation of websites and web services.*
</details>
<details>
  <summary><b>Spring Security</b></summary>

*Framework for securing Spring applications, adding authentication and authorization to protect resources.*
</details>
<details>
  <summary><b>Docker</b></summary>

*Platform for automating deployment and managing containerized applications, simplifying work with isolated environments.*
</details>
<details>
  <summary><b>Liquibase</b></summary>

*Tool for version control of database schemas, allowing controlled schema changes.*
</details>
<details>
  <summary><b>Lombok</b></summary>

*Library that automates code generation to reduce boilerplate cLombok: ode, such as getters, setters, and equals/hashCode.Lombok: ode, such as getters, setters, and equals/hashCode.*
</details>
<details>
  <summary><b>Mapstruct </b></summary>

*Library for automatic code generation of mappings between Java objects, simplifying conversion between different models.*
</details>
<details>
  <summary><b>Maven</b></summary>

*Tool for managing project dependencies, compilation, building, and publishing of Java programs.*
</details>
<details>
  <summary><b>MySQL </b></summary>

*Relational database that uses the SQL language for managing and interacting with data.*
</details><details>
  <summary><b>Swagger</b></summary>

*Tool for automatically generating API documentation, allowing developers to interactively engage with and understand the structure of the API.*
</details>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
### <img src="https://media2.giphy.com/media/CNjoBV8X6bA6kC1hmw/giphy.gif?cid=6c09b952o0m8p765cuilvts077hjtwc54yaqj2loypwf728l&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s" width="40"/>Project controllers:
**Authentication Controller** - endpoints with open access for new users who want to register and for registered users who want to log in.

| HTTP method |       Endpoint        |           Description           |
|:-----------:|:---------------------:|:-------------------------------:|
|    POST     | `/auth/registration ` |       Register a new user       |
|    POST     |    `/auth/login `     |   Login as a registered user    |



---
**Book Controller** - endpoints for managing books.

| HTTP method |    Endpoint    | Role  |    Description    |
|:-----------:|:--------------:|:-----:|:-----------------:|
|     GET     |   `/books `    |  ALL  |   Get all books   |
|     GET     | `/books/{id}`  |  ALL  |  Get book by Id   |
|    POST     |   `/books `    | ADMIN |  Save a new book  |
|   DELETE    | `/books/{id} ` | ADMIN | Delete book by Id |
|     PUT     | `/books/{id} ` | ADMIN | Update book by Id |
---
**Category Controller** - endpoints for managing categories.

| HTTP method |    Endpoint    | Role  |           Description            |
|:-----------:|:--------------:|:-----:|:--------------------------------:|
|     GET     |   `/categories `    |  ALL  | Get all categories |
|     GET     | `/categories/{id}`  |  ALL  |        Get category by Id        |
|     GET     | `/categories/{id}/books`  |  ALL  |   Get all books by category Id   |
|    POST     |   `/categories`    | ADMIN |         Save a new category        |
|   DELETE    | `/categories/{id} ` | ADMIN |        Delete category by Id         |
|     PUT     | `/categories/{id} ` | ADMIN |        Update category by Id         |
---
**Shopping Cart Controller** - endpoints for managing shopping cart.

| HTTP method |              Endpoint              | Role  |           Description            |
|:-----------:|:----------------------------------:|:-----:|:--------------------------------:|
|    POST     |             `/cart `             |  USER  | Add book to shopping cart |
|     GET     |     `/cart`    |  USER  |       Get shopping cart with books        |
|   DELETE    | `/cart/cart-items/{cartItemId}` | USER |       Delete book from shopping cart    |
|     PUT     |          `/cart/cart-items/{cartItemId}`          | USER |       Update book quantity in shopping cart        |

---
**Order Controller** - endpoints for managing orders.

| HTTP method |              Endpoint              | Role  |           Description            |
|:-----------:|:----------------------------------:|:-----:|:--------------------------------:|
|     GET     |             `/orders `             |  USER  | Get order history |
|     GET     |     `/orders/{orderId}/items`      |  USER  |        Get order items by order id        |
|     GET     | `/orders/{orderId}/items/{itemId}` | USER |        Get order item by order and item id    |
|    POST     |          `/orders`          | USER |        Place a new order and clear the cart        |
|    PATCH    |          `/orders/{id} `           | ADMIN |       Update order status       |
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
### <img src="https://media2.giphy.com/media/CNjoBV8X6bA6kC1hmw/giphy.gif?cid=6c09b952o0m8p765cuilvts077hjtwc54yaqj2loypwf728l&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s" width="40"/>You can test and use this project:
1. Install [Docker](https://www.docker.com/products/docker-desktop/)
2. Clone [THIS](https://github.com/nshtykh/online-book-store) repository
3. Configure a "**.env**" file with necessary environment variables
4. Run the command `mvn clean package`
5. Use `docker-compose up --build` to build and launch Docker containers
6. Access the locally running application at http://localhost:8088