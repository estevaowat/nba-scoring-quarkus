#!/bin/zsh

curl -X GET http://localhost:8080/score || exit 1

echo "executing loop"

while true; do
  echo "sending request"
  curl -X POST http://localhost:8080/score -H "Content-Type: application/json" -d '{"playerId": 1,"points": 2, "assists": 3, "rebounds": 2, "createdAt": "2024-05-16T22:58:40","transactionId": "transactionId_b0435cd58b3a"}' || exit 1
  echo "request sent"
done
