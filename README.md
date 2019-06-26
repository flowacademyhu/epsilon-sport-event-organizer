# Epsilon: Sport event organizer

## Description
This application stands for organizing sport events. You can login with your Google account, than create and manage your cups, teams, etc. As a user of the application, you can be entitled for the following:
- Cups - organizer or participant
- Teams - leader, member, guest

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
## Authors
- Dániel Juhász
- Zoltán Micsik
- Dániel Simon
- Tamás Viczián

## License
This project is licensed under the GNU License - see the [LICENSE.md](https://github.com/flowacademyhu/epsilon-sport-event-organizer/blob/master/LICENSE) file for details
