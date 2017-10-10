# vertx-http-jdbc
vertx example

= Vert.x Simplest Maven project

This project shows a very simple hello world Vert.x project using Maven, which has a simple HTTP server which
simply returns "Hello World!" to every request.

In this example Vert.x is used embedded. I.e. we use the Vert.x APIs directly in our own classes rather than deploying
the code in verticles.

You can run or debug the example in your IDE by just right clicking the main class and run as.. or debug as...

The pom.xml uses the Maven shade plugin to assemble the application and all it's dependencies into a single "fat" jar.

Using MY-SQL database
create database test;

CREATE TABLE profile (
  id varchar(10) NOT NULL,
  name varchar(100) DEFAULT NULL,
  address_id varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO test.profile (id, name, address_id) VALUES ('1', 'Om', '1');
INSERT INTO test.profile (id, name, address_id) VALUES ('2', 'abcd', '1');
INSERT INTO test.profile (id, name, address_id) VALUES ('3', 'def', '1');
INSERT INTO test.profile (id, name, address_id) VALUES ('4', 'xyz', '1');

To run with maven

    mvn compile exec:java

To build a "fat jar"

    mvn package

To run the fat jar:

    java -jar target/vertx-http-jdbc-3.4.2-fat.jar

(You can take that jar and run it anywhere there is a Java 8+ JDK. It contains all the dependencies it needs so you
don't need to install Vert.x on the target machine).

You can also run the fat jar with maven:

    mvn package exec:exec@run-app

Now point your browser at http://localhost:9999
