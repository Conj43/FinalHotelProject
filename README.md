CS 3330 Final Project
Group AA
Colin Buchheit, Kymani Scott, Drew Distler and Connor Joyce

Group AA's Hotel Manager
This application was made for a hotel's front desk staff to more efficiently keep track of customers and to perform the day to day operations that they encounter. This was primarily built with the idea that the front desk worker or receptionist would have access to it via a computer command line. The application supports many features which are listed below. There were four team members during the development of this application and each persons individual features are also listed.  

What we used to create this project:
- Language: Java 21
- Builder: Maven
- Source Control: Github
- Testing: JUnit 5
- Design Patterns Used
    - Strategy
    - Command
    - Singleton
- IDE: Eclipse

To run this application it is very simple, you will just need to download the source code and execute it in an environment ready to build using Maven. Main.java is the runnable file.


if you are wanting to test the features you can use this customer
         "customerID": 53,
        "firstName": "test",
        "lastName": "test",
        "email": "test",
        "phoneNum": "123",
        "address": "123",
        "birthDate": "2024-05-06",
        "age": 0,
        "isRewardsMember": true,
        "rewardPoints": 0,
        "cardNum": "123"
-this customer has a standard room reserved for 2024-05-06 through 2024-05-09


Colin’s features:
-	Define Room Types/number of rooms
-	Create Reservation
-	Cancel Reservation
-	Check Availability of rooms
-	Confirm Bookings 

Kymani’s features:
-	Create a system for specific requests (ex: extra pillows, extra towels, etc.)
-	Implement dynamic prices based on time of year, time of week, time of booking relative to reservation, etc. 
-	Allow customers to book conference rooms
-	Generate codes to use hotel pool/amenities and give them to user at time of check-in
-	Activate/deactivate key card



Drew’s features:
-	Check into room
-	Check out of room
-	Sign up for loyalty program
-	Use loyalty points
-	Pay bill




Connor’s features:
-	Create database previous customers (in csv file)
-	Use singleton design pattern to create a manager of the database of previous customers
-	Allow customers to create profile (name, email, age, job, etc.)
-	Keep database of past customers
-	Take precautions to keep guest data secure
-	Handle errors
-	Generate reports for specific dates and rooms (using database)


All features:
•	Define room types and number of rooms
•	Create a reservation
•	Check in/out
•	Cancel a reservation
•	Check availability of rooms
•	Confirm bookings (display all information when room is booked and ask customer if correct)
•	Make payment (use strategy design for different types of payment)
•	Activate/deactivate key card
•	Create database previous customers (in csv file)
•	Use singleton design pattern to create a manager of the database of previous customers
•	Allow customers to sign up for loyalty program and use points to get discounts
•	Take precautions to keep guest data secure
•	Handle errors
•	Generate reports for specific dates and rooms (using database)
•	Create system for specific requests (ex: extra pillows, extra towels, etc.)
•	Implement dynamic prices based on time of year, time of week, time of booking relative to reservation, etc. 
•	Allow customers to book conference rooms
•	Generate codes to use hotel pool/amenities and give them to user at time of check-in
•	Allow customers to create profile (name, email, age, job, etc.)
•	Keep database of past customers

