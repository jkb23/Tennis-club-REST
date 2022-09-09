# Tennis club reservation system
Spring Boot server application to create reservations for tennis court.

# Neccesary software
* MySQL https://dev.mysql.com/downloads/installer/

# Useful software
* Postman https://www.postman.com/downloads/

# Start database
First start the database with MySQL workbench mentioned above by following steps.

To create new connection enter parameters as displayed below

```
username = root
password = passwd
port = 3306
```

After the connection is created, create new schema by right-clicking on small windows on the left called "SCHEMAS".
It should be called tennis_schema.

Now the database is up and running.

# Working with app

In this part of readme I will be using Postman, that i mentioned in "Useful software", to show funcionality.
Jump to "How to use Postman" part of this readme if needed

Run "TennisClubApplication.java" and Postman.

## Add court
How to add court to court database

* Set request to "POST"
* Set last part of URL to "addCourt"
* Write following to the body:

```
surface:
```
As value (rights side of ":") there can be these surfaces: "GRASS", "CLAY", "HARD", "ARTIFICIAL".

Each surface has it's own price per minute.

| Surface     | Price per minute |
|-------------|------------------|
| Grass       | 0.16€            |
| CLAY        | 0.12€            |
| HARD        | 0.18€            |
| ARTIFICIAL  | 0.14€            |

Each court will be assigned with unique id.

## Add reservation
How to add reservation to reservation database

* Set request to "POST"
* Set last part of URL to "addReservation"
* Write following to the body:

```
courtId:
doubles:
start:
end:
customerName:
customerPhoneNumber:
```

Example with values:

```
courtId:1
doubles:true
start:2022-1-1 15:00:00
end:2022-1-1 16:00:00
customerName:Ignac
customerPhoneNumber:+421903852867
```

### Parameters explanation:

```
courtId - id of court the game will be playen on (court with that id has to exist)
doubles - true if the game is 2 versus 2, false if 1 versus 1
start - start time of game in format yyyy-[m]m-[d]d hh:mm:ss[.f...]
end- end time of game in format yyyy-[m]m-[d]d hh:mm:ss[.f...]
customerName - name of the customer making the order (it has to contain only lettersand spaces and has to be 3 to 29 characters long)
customerPhoneNumber - phone number of customer making the order (it has to start with country preset, that means it has to start with '+', followed by 12 digits)
```

Each reservation will be assigned with unique id.

The function will return price of the reservation determined by time, surface and if the game is doubles.
If the reservation cannot be made function will return "1.0"

## Get reservations bound with court id
How to see list of all reservations that were or are going to be played on specific court.

* Set request to "GET"
* Set last part of URL to "res_by_court_id"
* Write following to the body:

```
courtId:
```

courtId - id of court the game will be playen on (court with that id has to exist)

The function will return list of all suitable reservations.

## Get reservations bound with customer phone number
How to see list of all reservations that were or are going to be played by specific customer.

* Set request to "GET"
* Set last part of URL to "res_by_customer_phone"
* Write following to the body:

```
customerPhoneNumber:
```

customerPhoneNumber - phone number of customer making the order (it has to start with country preset, that means it has to start with '+', followed by 12 digits)

The function will return list of all suitable reservations.

## Get courts
How to see list of all courts.

* Set request to "GET"
* Set last part of URL to "courts"

The function will return list of all courts.

# How to use Postman

In this part I will show how to use Postman app.

* Create new request by clicking "+"
* Set GET/POST request depending by what you want to do
* Set URL to "localhost:8080/x" and replace "x" on what you want to do
* (Optional, depending on what you want to do) Go to Body -> form-data -> Bulk Edit and fill parameters depending on what you want to do

The "what you want to do" part is specified in "Working with app" part of thies readme.
