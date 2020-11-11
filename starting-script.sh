#!/bin/bash

echo "BUILDING THE PROJECT"
./gradlew clean build

echo "RUNNING PROJECT AUTOMATED TESTS"
./gradlew clean test

echo "DOWNLOADING AND STARTING KAFKA"
chmod +x start-kafka.sh
./start-kafka.sh

echo "BUILDING DOCKER IMAGES"
sudo docker-compose build

echo "RUNNING DOCKER IMAGES"
sudo docker-compose up
