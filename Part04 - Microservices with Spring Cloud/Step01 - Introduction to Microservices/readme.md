# Step 01 - Introduction to Microservices

## Definition

There are multiple definitions:

- Small autonomous services that works together.
- in short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API...
- These services are built around business capabilities and independently deployable by fully automated deployment machinery...
- There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.

---

## Components

- REST api
- Small Deployable Units
- Cloud Enabled

Cloud enable means multiple instances for each microservice.

---

## Challenges

#### Bounded Context

Since it is not possible to define all the microservices boundaries at the start, they could need adjustment during the developing process.

#### Configuration Management

Each microservice can have multiple instances in each environment and can be multiple environments.

All these infrastructures must be built and managed.

#### Dynamic Scale Up and Scale Down

There is different workload for each microservice and this load changes over time.

#### Visibility

A logging infrastructure is necessary to identify bugs and a management system to monitor the microservices status.

#### Pack Of Cards

Because in the microservices infrastructure there is a chain of dependencies between microservices if one of those at the base crashes then all the others stop working.

---

## Spring Cloud

Spring Cloud provides a set of services to manage some of  the previous challenges:

- **Configuration Management**
  - Spring Cloud Config Server: provides a centralized location for configurations storage.
- **Dynamic Scale Up and Scale Down**
  - Naming Server (Eureka).
  - Ribbon (Client Side Load Balancing).
  - Feign (Easier REST Clients).
- **Visibility And Monitoring**
  - Zipkin Distributed Tracing.
  - Netflix API Gateway.
- **Fault Tolerance**
  - Hystrix.

---

## Microservice Advantages

#### New technologies And Process Adaptation

Each microservice can be built in different language.

#### Dynamic Scaling

A microservice can scale dynamically with the requests.

#### Faster Release Cycles

Since the components are smaller it is easier release them.

---

## Microservice Components

Each microservice needs a specific port, some services has a pre-configured port.

Each microservice has a specific url.
