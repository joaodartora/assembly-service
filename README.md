# Assembly Service

## Purpose 

- This service can be used to manage different operations of an assembly, using a REST API's.

## Possible Operations

- Create new agenda.
- Open a voting session to a given agenda.
- Vote on agenda.
- Get results from an agenda.

## Running

#### Needed dependencies

- **Docker** - Recomended version: 19.03.13
- **Docker-Compose** - Recomended version: 1.25.5

#### Running with script

1. Give permission to execute on the starting script with ```chmod +x start-script.sh```
2. Execute the starting script with ```./starting-script.sh```

#### Running manually

1. Build the project with ```./gradlew clean build```
2. Run automated tests with ```./gradlew clean test```
3. Get Kafka up locally with ```./start-kafka.sh```
4. Build docker images with ```docker-compose build```
5. Run docker images with ```docker-compose up```

- If you need to stop Kafka, just run the command ```chmod +x stop-kafka && ./stop-kafka.sh```
- After running manually or with script, the service will be available at the ip address ```http://localhost:8080``` where you can find all the API documentation too.

## Load/Perfomance Test

- To execute the **Gatling** load test, first you need to certify that the service is up and running on ```http://localhost:8080```
- After make sure the service is up and running, click [here](/stress-test) and follow the instructions

## Tech Choices

- **Java 11**: Java is a stable and power language to build microservices, the version 11 of Java brings a lot of performance and security improvements over version 8.
- **JUnit 5**: The latest version brings some good features like assertAll() and assertThrows(), that gives us more power on unit testing. 
- **SpringBoot 2.3.5:** SpringBoot is a complete framework to build cloud native applications easily, having a wide range of integrations with different tools.  
- **Gradle 6.6.1:** Gradle is a robust build tool, which make possible to run builds scripts easily.
- **Swagger 2.9.2:** Swagger is probably one of the best documentation libs and widely used.
- **Apache Kafka 2.5.0**: Apache Kafka is a very powerful data processing platform, it is highly scalable and easily configurable at the consumer / producer level. 
- **MySql 8**: The MySQL database is one of the most stable today, I chose it because it is easily configurable and allows a certain level of relationship between data.
- **Docker/Docker Compose**: Docker is probably the most famous container tool in the world, it helps a lot on building and deploying any application. 
- **Gatling Test**: Gatling is a very good tool to execute load and performance tests on a application, it's easy to configure and to run.