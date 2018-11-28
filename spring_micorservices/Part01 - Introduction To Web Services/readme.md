# Part 01 - Introduction To Web Services

## Web Service Definition

'Software system designed to support interoperable machine-to-machine interaction over a network' - *W3C*

Key concepts:

1. designed for machine-to-machine (application-to-application)
2. should be interoperable - not platform dependent
3. should communicate over a network

---

## Data exchange

in order to be be interoperable, data exchange have to be platform independent. Some possible data format are:
- **XML** - eXtensible Markup Language
- **JSON** - JavaScript Object Notation

## Service Definition

The service definition specify indispensable information for the data exchange:
- **Request/Response Format**
- **Request Structure**
- **Response Structure**
- **Endpoint** - how to call the service.

---

## Terminology

- **Request** - the input to the web service,
- **Response** - the output from a web service.
- **Message exchange Format** - the format of the request and the response, like XML or JSON.
- **Service Provider or Server** - hosts the web service.
- **Server Consumer or Client** - consumes the web service, who is using the service.
- **Service Definition** - see above.
- **Transport** - how a service is called, like HTTP or MQ.

---

## Web Service Groups

### SOAP - Simple Access Object Protocol

Uses XML as the request exchange format.

Defines a specific XML request and response structure:
- **Header** - contains authentication, authorization, signatures and other similar informations.
- **Body** - contains the content of request or response.

It can use HTTP or MQ for Transport.

The service is defined by **WSDL** (Web Server Definition Language), which defines:
- **Endpoint**.
- **All Operations**.
- **Request Structure**.
- **Response Structure**.


### REST - REpresentational State Transfer

It use http protocol for the requests and the responses.

It uses resources. Resources have:
- **URI** - Uniform Resource Identifier, like /user/hone.
- different representations, like XML, HTML, JSON.
- **** - .

Example of **CRUD** (Create/Read/Update/Delete) operations are:
- Create an user: `POST /users`
- Delete an user: `DELETE /users/1`
- Get all users: `GET /users`
- Get one users: `GET /users/1`

JSON is popular as data exchange format but is not mandatory.

There is any standard for service definition.
- **WADL** - Web Application Definition Language, not very popular.
- **Swagger** - more popular.

### REST vs SOAP

In SOAP there is an only one possible data exchange format, in REST there is XML, HTML, JSON or any other data format.

SOAP uses WSDF for the service definition, REST has not a standard definition Language, Swagger is one of the popular standards.

SOAP can use both HTTP and MQ as transport, REST uses HTTP.
