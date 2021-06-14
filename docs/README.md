# Messaging app

## Technologies 
    Java 11
    Spring boot
    Apache kafka
    PostgreSQL
    OpenAPI + Swagger
    Maven
    JUnit

## API actions

### Create account
#### Request
- GET http://localhost:8080/v1/users
#### Response
- HTTP 200
```json
{
    "uid": 7,
    "nickname": "testuser4"
}
```
### Send message
#### Request
- POST http://localhost:8080/v1/users/2/messages
#### Response
- HTTP 200
```json
{
    "sender_uid": 2,
    "receiver_uid": 1,
    "content": "This is a test message 10"
}
```
### List received messages
#### Request
- GET http://localhost:8080/v1/users/1/messages/received
#### Response
- HTTP 200
```json
[
    {
        "message_id": 3,
        "sender_uid": 2,
        "receiver_uid": 1,
        "content": "This is a test message 3"
    },
    {
        "message_id": 31,
        "sender_uid": 2,
        "receiver_uid": 1,
        "content": "This is a test message 4"
    }
]
```
### List sent messages
#### Request
- GET http://localhost:8080/v1/users/1/messages/sent
#### Response
- HTTP 200
```json
[
    {
        "message_id": 1,
        "sender_uid": 1,
        "receiver_uid": 2,
        "content": "This is a test message 1"
    },
    {
        "message_id": 2,
        "sender_uid": 1,
        "receiver_uid": 2,
        "content": "This is a test message 2"
    }
]
```
### List received messages by sender
- GET http://localhost:8080/v1/users/1/messages/received/2
```json
[
    {
        "message_id": 3,
        "sender_uid": 2,
        "receiver_uid": 1,
        "content": "This is a test message 3"
    },
    {
        "message_id": 31,
        "sender_uid": 2,
        "receiver_uid": 1,
        "content": "This is a test message 4"
    }
]
```

## Dependencies
    Kafka
        1. Use: docker-compose -f docker-compose-kafka.yml up -d
    PostgreSQL
        1. Use: docker-compose -f docker-compose-postgres.yml up -d
        2. Create schema with sql/testdb.sql

## How to build it?
    ./mvnw clean install

## How to run it?
    ./mvnw spring-boot:run

# How to run tests
    ./mvnw integration-test

## Build docker image
    ./mvnw package spring-boot:repackage
    docker build -f docker/Dockerfile -t messaging-app:1.0.0 .

## Swagger
    http://localhost:8080/api-docs
    http://localhost:8080/swagger-ui.html

## Room for improvement
    REST improve http error per validation, currenlty only 500 is returned
    Kafka idempotency (deduplicate messages)
    Kafka in cluster mode
    Kafka persistence
    Test coverage + ITs
    Endpoints result pagination
