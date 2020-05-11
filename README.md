# RestaurantApp

### Functionality

Restaurant system provides next functionality:

* Login and authorization for users
* Looking through menu 
* Checking dishes and their ingredients
* Ordering lunches at a certain time
* Viewing all orders
* Invoicing bills due to orders
* Admin can manage passenger's orders (accept or not)

### Technology stack

* Java 8
* Maven
* Servlets
* JSP
* JSTL
* CSS
* Hikari connection pool
* Apache Tomcat
* log4j
* MySQL
* H2
* Mockito
* Junit

### Deployment requirements

* Java 1.8 or later
* Maven 3.0 or later
* Apache Tomcat 9.0 or later


## Installation

### Clone and build
First, clone the project from github:
```
$ git clone https://github.com/Elizabethssss/RestaurantApp
```
Then, install it:
```
$ cd RestaurantApp
$ mvn clean install
```

### Configure mysql
* change credentials at (__src/main/resources/properties/db.properties__) to your own
* use provided mysqldump at (__src/main/resources/database/schema.sql__) before first run of application


### Run application
* Set up project as web project
* Enable maven auto-import
* Set up project Java SDK
* Make war from module at Project structure/artifact menu
* Add Run configuration based on Apache tomcat Local server instance
* Run application with mentioned above