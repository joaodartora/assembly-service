#!/bin/sh

echo "UPDATING LINUX AND INSTALLING UTILITIES"
apt-get update -y
apt-get install wget -y && apt-get install gzip -y && apt-get install tar -y

echo "DOWNLOAD AND UNTAR KAFKA"
wget https://downloads.apache.org/kafka/2.5.0/kafka_2.12-2.5.0.tgz && tar -xvf kafka_2.12-2.5.0.tgz
cd kafka_2.12-2.5.0/

echo "STARTING ZOOKEEPER"
bin/zookeeper-server-start.sh config/zookeeper.properties &
sleep 10

echo "STARTING KAFKA"
bin/kafka-server-start.sh config/server.properties &
sleep 10

echo "CREATING AGENDAS RESULTS TOPIC"
bin/kafka-topics.sh --zookeeper localhost:2181 --topic agendas-results --create --partitions 3 --replication-factor 1 &
