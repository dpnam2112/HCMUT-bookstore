# HCMUT Bookstore
A simple web application for a small bookstore.

## Set up the environment for development
- Install MySQL server.
- Install a Java IDE (IntelliJ IDEA is highly recommended. Ecllipse and NetBean are popular alternatives, but they are not my favors).
- Use Gradle to install all dependencies. If you use IntelliJ IDEA, the installation should be done automatically.

## How to run
- Use a MySQL client to create a database and in that database, create a table 'Person' with 4 fields: id (primary key, int), firstname (varchar), lastname (varchar), age (int).
- You can find MySQL configurations in AppConfig.java. Change the configurations as you like (username, password, .etc)
- Run the project and go to 'localhost:8080/api/person/' to check if the application returns any result. There should be a list of information in JSON format.
