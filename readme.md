# Status-Inquiry-Service ##

This is a sample application which is developed using mSpace. The purpose of this application is to notify 
the user with the latest status of their submissions by a sms.Using the web UI admin can upload details of 
the user with the reference number the initial status would be "PENDING" after that the admin can update the 
status accordingly. When the user send a sms with the reference number, current status will receive as a reply.

## Prerequisites ##

* Java 8.x
* MySQL 8.x
* Maven 3.x


## How to Build and Deploy ##

* Build the project from top-level.  
```
  mvn clean install
```

* Go inside the `target/service-status-inquiry/`.  

* Run following command to run the application. 

```
    ./bin/service-status-inquiry console
```

### System Level Configurations ###

Add following DNS to `/etc/hosts` file.

```
127.0.0.1       db.mysql.passport
127.0.0.1       core.passport.service
```

### Database Setup ###

* Run ``document_store_init.sql`` located at ``data`` directory to initialize the database.
