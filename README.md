# smFinanceToDo
Sm Finance To Do App

## Technology requirements
* Java 17
* Maven version 3.3.9 or above
* MySQL version 5.7 or above

## Database Setup
* Create MySQL database with name smfinance.
* Create user smfinance with password smfinance.
* Grant all privileges on database smfinance to user smfinance.
* To use custom database credentials, either update the file `src/main/resources/application.properties`.

## Adding lombok on IDEs
To check the code and compile it on IDEs, the lombok plugin needs to be added manually to the IDE.

## Running project
* To run the project from code base, run the command `mvn spring-boot run`
* To build the project as a war file, run the command `mvn clean install`
    * Add the war file to a Java server webapps.
* The database should be built on start up.

## Tasks completed
- All tasks given in the specification.

## Rest API URLs using curl command
* To view all to-do tasks, the URL is `http://localhost:8080/to-dos`
* Create a to-do task: `curl -X POST -H 'Content-Type: application/json' http://localhost:8080/to-do/create --data '{ "priority": <1|2|3|4|5|6>, "name": "Task", "description: "Task description", "status": <1|2|3|4|5|6> }'`
* Update a to-do task: `curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/to-do/update/<task id> --data '{ "priority": <1|2|3|4|5|6>, "name": "Task", "description: "Task description", "status": <1|2|3|4|5|6> }'`
* Mark a to-do task as complete: `curl -X POST -H http://localhost:8080/to-do/mark-complete/<task id>`
* Delete a to-do task: `curl -X DELETE -H http://localhost:8080/to-do/delete/<task id>`

Priority and status are set to be from 1 to 6 only.
 
## Scope for improvement
1. Adding more unit tests.
2. Adding custom validations by utilising the different groups.
3. Improving code efficiency by removing additional checks and adding validators as required.
