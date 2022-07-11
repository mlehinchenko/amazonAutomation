#!/bin/sh
# Environment Variables
# HUB_HOST

echo "Checking if hub is ready - $HUB_HOST"
attemptsCount=0
while [ "$( curl -s http://$HUB_HOST:4444/status | jq -r .value.ready )" != "true" ]
do
  sleep 1
  if [ $attemptsCount -eq 10 ];
    then
      break
    fi
    echo "Attempts connect to grid hub is: $attemptsCount"
    attemptsCount=$(($attemptsCount+1))
done || exit 1
echo "Hub is ready - $HUB_HOST"
# start the maven command
mvn test -Dsuite=simple_amazon -Denv=amazon -DHUB_HOST=$HUB_HOST -Dconfig=docker-driver