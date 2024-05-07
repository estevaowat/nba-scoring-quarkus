#!/bin/zsh
docker compose down --remove-orphans
./gradlew build
docker compose up -d --build
docker logs nbascoring1 -f

### Build application using native build
### docker compose down --remove-orphans && ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true && docker compose up -d --build && docker logs nbascoring -f
