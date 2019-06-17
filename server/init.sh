#!/bin/bash
echo "Starting DB..."
docker-compose up -d

 sleep 15

 echo "DB Started!"
echo "Init database and role"

docker exec -it server_my_postgres_1 psql -U postgres -c "create database sport_event_organizer;"
docker exec -it server_my_postgres_1 psql -U postgres -c "create user sport_event_organizer;"
docker exec -it server_my_postgres_1 psql -U postgres -c "alter user sport_event_organizer with encrypted password '123456';"
docker exec -it server_my_postgres_1 psql -U postgres -c "grant all privileges on database sport_event_organizer to sport_event_organizer;"

 echo "Init database and role ended!"