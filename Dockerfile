FROM eclipse-temurin:latest
COPY build/* .
ENTRYPOINT ["app"]