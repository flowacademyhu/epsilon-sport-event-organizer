# Epsilon: Sport event organizer

## Description

## Requirements
- JRE 11
- Nodejs (8.15.0+)
- Angular 7 (with cli)
- Docker

## Usage (Linux/MacOS)
#### 1. Run infrastructure
```bash
cd server
# First run
./init.sh
# Other run
docker-compose up -d
```
#### 2. Run server
```bash
cd server
./mvnw spring-boot:run
```
#### 3. Run frontend
```bash
cd client
ng serve
```

## Run tests
```bash
cd server
./mvnw clean test
```