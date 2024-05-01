#!/bin/zsh
while true; do
  sleep 0.5
  echo "sending request"
  curl http://localhost:8080/player
  echo "request sent"
done
