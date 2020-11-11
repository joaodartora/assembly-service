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

- 