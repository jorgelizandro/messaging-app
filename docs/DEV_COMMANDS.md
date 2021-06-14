# Dev commands

## Connect to Postgres
    docker exec -it postgres sh
    psql testdb testuser changeit
    psql -h localhost -p 5432 -U testuser testdb
