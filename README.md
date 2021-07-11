# Treasurehunt
Code for searching treasure in a treasure map	

The application is REST api that uses springboot framework which runs at port 8080

The UI is coded in Vue.js framework and is part of the project itself

# How to run the application

From the project folder run

./mvnw clean install 

./mvnw spring-boot:run

Go to http://localhost:8080 to see the UI. The UI uses VueJS framework
 ***
# API 
The openapi swagger UI can be found at (http://localhost:8080/swagger-ui)


 POST: /treasurehunt/map 

```
//Request body schema
{
    nodes:   [
                  {
                   "row": 1,
                  "column": 3,
                  "clueValue": 32
                  },
             ]
}
```
