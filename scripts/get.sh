#!/bin/zsh
while true; do
  sleep 1.5
  echo "sending request"
  curl -X POST http://localhost:8080/score
  echo "request sent"
done
