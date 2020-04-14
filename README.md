# sms-voting

Using Twilio, Spring-boot, and MySQL to build a WebApp for Voting

# Introduction

This repository contains a sample project on how to vote for candidates in an election using Twilio's SMS API.


# To test this Application

You will need `docker` and `docker-compose`. Optionally, use `MySQL Workbench (for a GUI database view)`.

Clone this repository and navigate into the `sms-voting` project directory.


## Setting up Application

1. Stop any MySQL57 service instance currently running to free port 3306.
2. Run the follwing command to build and start the application's services:
```bash
> docker-compose up -d --build
```

## Connecting to Application Database
1. Using Terminal:
   Run the follwoing command on the voting-db container
    ```
    docker exec -it voting-db /bin/bash
    > mysql -uroot -p
    > enter password 'password'
    > use table votes;
    
    query the table 'candidate' to view the candidates you will create from the webapp
    ```
    
2. Using MySQL Workbench:
   Open your local MySQL Workbench application and create a new MySQL Connection.
   Use the credentials below to log in:

    ```
    Hostname: <your ip address>
    Port: 3306
    Username: root
    Password: password
    ```

## Connecting to Application UI
1. Open your web bowser of choice and go to the address `http://localhost:9000`
2. Navigate to the new candidates page to create your candidates.
