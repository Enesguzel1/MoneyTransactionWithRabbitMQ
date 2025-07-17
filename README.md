
# Money Transaction With RabbitMQ

In this project, the money transfer process is implemented using RabbitMQ for message queue management, Spring Boot for the backend service, MongoDB for data management, and OpenAPI Specification (Swagger) for API documentation.


## API Reference

#### List Customer

```http
  GET /Customer
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `none`    | `CustomerDTO` | List all customers |

#### Add Customer

```http
  POST /Customer
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `body`      | `CustomerDTO` | **Required**. Customer data to create|

#### Start Transaction

```http
  POST /Transaction
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `body`      | `TransactionDTO` | **Required**. Transaction details in JSON format|

### CustomerDTO Example

```json
{
    "id": "64b7f1f2e7eec45612345678",
    "firstName": "John",
    "balance": 1750.75,
    "lastNotification": "empty"
}
```

### TransactionDTO Example

```json
{
  "fromCustomerId": "64b7f1f2e7eec45612345678",
  "toCustomerId": "64b7f1f2e7eec45687654321",
  "amount": 100.0
}
```



## Installation

Clone the repository and navigate into it:

```bash
git clone https://github.com/Enesguzel1/MoneyTransactionWithRabbitMQ.git
cd MoneyTransactionWithRabbitMQ
```
Download the dependencies and build the project with Maven:
```bash
  mvn clean install
```
Start the required Docker containers using Docker Compose:
```bash
  docker-compose -f ./docker-compose.yml up -d
```
    
