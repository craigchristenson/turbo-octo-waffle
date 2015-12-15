# Business

This is an example server application which will provide the business logic layer.
This will only be accessable to the server application.

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/)
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (JDK 8)
* [Maven](https://maven.apache.org/)

## Installation

* `git clone <repository-url>` this repository
* change into the new directory
* `mvn package`

## Running / Development

* `java -jar target/business-1.0-SNAPSHOT.jar server`
* The server will communicate with this app at [http://localhost:8080](http://localhost:8080).
