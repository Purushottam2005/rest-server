# Overview

This is a simple REST service template which I've used in a number of projects. It is based on __Spring__ and __RESTEasy__
and with a lean DAO objects based on the Spring __SimpleJDBCTemplate__ and __RowMapper__ classes.

You can use Hibernate or other ORM tools if you like. In the end my preference was _the leaner - the better_. That's why
this template is not using Hibernate.

The dependency management is done with __Maven__ so you need to have that somewhere in your path. If that is not your
cup of tea you can replace it with something else of course.

## Architecture

If you have worked with Spring before then the architecture is quite straight forward. For the others I've compiled a
short rundown on the files.

<pre>
src
└── main
    ├── java                    <-- java files live here
    │   └── com
    │       └── goleft
    │           └── rest
    │               ├── dao
    │               ├── entity
    │               ├── log
    │               ├── service
    │               └── util
    ├── resources               <-- config files live here
    ├── sql                     <-- sql files live here
    └── webapp                  <-- HTML files live here
        └── WEB-INF             <-- Spring config files live here 
</pre>

## MySQL setup
You need to create a database for the service to function. In the default configuration
the service expects a database _rest_ on _localhost_. You can change this in
_src/main/resources/config.properties_

To create the database connect to your MySQL instance as __root__ and run the following
commands
<pre>
mysql -h localhost -u root -p
mysql> create database rest;
mysql> grant all on rest.* to 'rest'@'localhost' identified by 'rest';
mysql> flush privileges;
mysql> quit;
</pre>

Then load the database with
<pre>
mysql -h localhost -urest -prest rest < src/main/sql/rest.sql
</pre>

## Project setup
As the final step you use __maven__ to download the depedencies and build the .war file.

Run the following command to download all dependencies and to build the service.
<pre>
mvn package
</pre>

Then run this command to run the service.
<pre>
mvn jetty:run
</pre>

Now you should be able to access it under http://localhost:8080/rest/
