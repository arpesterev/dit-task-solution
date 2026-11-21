# DIT task solution

A small Java 11 / Spring Boot REST service implementing two endpoints: one accepts a request to print text repeatedly, and the other reports usage of that operation.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/first-endpoint` | Passes a request to the repeated-printing service |
| `GET` | `/second-endpoint` | Returns the service's usage information |

The POST body is mapped to `PrintMe`:

```json
{
  "username": "demo",
  "countRepeats": 2,
  "delayedTimeout": 1000,
  "text": "Hello"
}
```

`countRepeats` is the repetition count and `delayedTimeout` is the delay in milliseconds. The JSON above illustrates the DTO shape; it is not an automated test result.

## Run locally

With Java 11 and Maven installed:

```sh
mvn spring-boot:run
```

The project declares Spring Boot 2.7.18 and `springdoc-openapi-ui` in `pom.xml`. Runtime configuration is in `src/main/resources/application.properties`.

## Code map

- [Controller](src/main/java/ditsolv/controllers/SimpleMicroserviceController.java): API paths and request/response contracts.
- [Request DTO](src/main/java/ditsolv/dtos/PrintMe.java): request fields.
- [Services](src/main/java/ditsolv/services/): endpoint interfaces.
- [Implementations](src/main/java/ditsolv/services/impl/): request handling and usage tracking.

This repository is an implementation of a technical task, not a general-purpose production service. The launch command has not been executed as part of this documentation change.
