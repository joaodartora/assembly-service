#!/bin/sh

cd kafka_2.12-2.5.0/

echo "STOPPING KAFKA"
bin/kafka-server-stop.sh config/server.properties &
sleep 15

echo "STOPPING ZOOKEEPER"
bin/zookeeper-server-stop.sh config/zookeeper.properties &



