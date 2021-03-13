





curl -v localhost:8080/parks
curl -v localhost:8080/parks/1
curl -X POST localhost:8080/parks -H 'Content-type:application/json' -d '{"name": "Green Park"}'
curl -X PUT localhost:8080/parks/1 -H 'Content-type:application/json' -d '{"name": "Summer Park"}'
