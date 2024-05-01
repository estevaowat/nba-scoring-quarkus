#!/bin/zsh
docker compose down && ./gradlew build && docker compose up -d --build && ./scripts/get.sh
