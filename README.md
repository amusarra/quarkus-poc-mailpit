# Mailpit POC with Quarkus

This project demonstrates how to integrate Quarkus with Mailpit for local email testing during development.
It provides a simple example of sending an email from a Quarkus application
and verifying that it is received by Mailpit.

The project is related to the blog post on the [Codemotion Magazine](https://www.codemotion.com/magazine/it/author/amusarra/).

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Functionality

The application exposes a REST endpoint that, when called, sends a simple email. Mailpit is used as an in-memory email 
testing tool, capturing all emails sent by the application. This allows developers to verify email sending functionality 
without configuring a real SMTP server or sending actual emails.

## Modules Used

-   `quarkus-rest`: Provides the REST endpoint for triggering the email sending.
-   `quarkus-arc`: Core module for dependency injection.
-   `quarkus-mailer`: Simplifies sending emails.
-   `quarkus-mailpit`: Integrates Mailpit with Quarkus for local email testing.
-   `quarkus-mailpit-testing`: Provides utilities for testing Mailpit integration.
-   `quarkus-junit5`: JUnit 5 extension for Quarkus.
-   `rest-assured`: Used for testing the REST endpoint.

## Prerequisites

-   Java 21+
-   Maven 3.9+
-   Docker or Podman (optional, for running Mailpit in a container)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-poc-mailpit-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Mailer ([guide](https://quarkus.io/guides/mailer)): Send emails with a simple and easy to use API.
- Configuration ([guide](https://quarkus.io/guides/config)): Configure your application with application.properties, YAML files, environment variables and more.
- Testing ([guide](https://quarkus.io/guides/getting-started-testing)): Write tests for your Quarkus applications.
