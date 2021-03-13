

* The API spec (OpenAPI specification is acceptable)
- added in parks_api.json
* How did you decide which technologies to use as part of your solution?
- Spring Boot due to less configuration instead Tomcat ( embedded/ standalone )
- Jpa instead Jdbc due to entities mapping directly into database tables
* Are there any improvements you could make to your submission?
- yes run the tests easier 
* What would you do differently if you were allocated more time?
do a script to run the tests automatically.


curl -v localhost:8080/parks
curl -v localhost:8080/parks/1
curl -X POST localhost:8080/parks -H 'Content-type:application/json' -d '{"name": "Green Park"}'
curl -X PUT localhost:8080/parks/1 -H 'Content-type:application/json' -d '{"name": "Summer Park"}'
