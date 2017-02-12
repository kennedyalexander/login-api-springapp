# login-api-springapp
My overly fancy loginapi

Hi Welcome to my spring api

This Api is built using Servlet 3, Spring Security and Spring annotations.
It uses a h2 database instance that is span up when the server is run on tomcat.
Please check the sql files in /resources in order to look at the inital data and table structure.
To run just drop the built .war file in tomcats /webapps folder and it will startup.


This api only accepts POST REQUESTS 
It consumes JSON 
Request Examples:

http://localhost:8080/LogonRestful-0.0.1-SNAPSHOT/api/login/
Example Json Request:
{
  "email": "email@email.com",
  "password": "password"
}
Possible responses:
true,
false.

http://localhost:8080/LogonRestful-0.0.1-SNAPSHOT/api/create/
Example Request Json:
{
  "username": "username",
  "password": "password",
  "email": "email@email.com",
  "active": "1"
}
Possible responses:
No possible responses if fails fails quietly.

http://localhost:8080/LogonRestful-0.0.1-SNAPSHOT/api/read/
Example Request Json:
{
  "email": "email@email.com",
  "password": "password"
}

Example responses:
{
"username": "oranges",
"email": "email@email.com",
"password": "60345106310221792267675897860362",
"active": 1
}

http://localhost:8080/LogonRestful-0.0.1-SNAPSHOT/api/update/
Example Request Json:
{
  "username": "username",
  "password": "password",
  "email": "email@email.com",
  "active": "1"
}
Possible Responses:
"Added as new user",
"Invalid username",
"Invalid Active",
"Invalid password",
"Updated user"


http://localhost:8080/LogonRestful-0.0.1-SNAPSHOT/api/delete/
Example Request Json:
{
  "email": "email@email.com",
  "password": "password"
}
Possible Responses:
"Deleted user",
"Not Deleted user".
