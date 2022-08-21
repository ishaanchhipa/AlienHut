-->Title of the project:
Movie Ticket Booking System

-->Introduction:
This software project is a Cinema Booking System. It has two types of users, being the 
customers and admin. 
The customers can log in, view the movies in the system, and book seats for a specific 
date, film and time. The employees can export a list containing information (number of 
bookings, user, date, time and seats) about particular film.

-->Software Specification:
▪ Software environment requires that an IDE(like NetBeans) be 
configured with JDK 17.1+
▪ MySQL 8+ running on a server such as WAMP or XAMPP server on the 
same question.

-->Modules:
I. Login Page
II. Signup Page
III. Customer View Page
IV. View Profile Page
V. Edit Profile Page
VI. View Films Page
VII. View Bookings Page
VIII. Booking Page
IX. Admin View Page
X. Admin View Film Page
XI. Admin View Film Booking Page


-->Setting up SQL database:

create database alienhut;

use database alienhut;

create table user(username varchar(30),firstname varchar(20),lastname varchar(20),password varchar(20));

create table films(movieid varchar(2), title varchar(50),description varchar(200));
insert into films values("1","Morbius","Morbius is a 2022 American superhero film based on the Marvel Comics character of the same name, produced by Columbia Pictures in association with Marvel.");
insert into films values("2","Batman","The Batman is a 2022 American superhero film based on the DC Comics character Batman. Produced by Warner Bros.");
insert into films values("3","The Boss Baby","The Boss Baby is a 2017 American computer-animated comedy film produced by DreamWorks Animation and distributed by 20th Century Fox.");
insert into films values("4","Multiverse Of Madness","Doctor Strange in the Multiverse of Madness is a 2022 American superhero film based on Marvel Comics featuring the character Doctor Strange.");

create table booking(movieid varchar(2), user varchar(30),title varchar(50),date varchar(12),time varchar(10),seats varchar(30));


!!!Note:
1. Set SQL database first. (Queries written above)
2. Signup before login.
   Password should contain atleast 1 special character,1 capital letter, 1 number and length of 8.
   Username should contain atleast 1 number and length of 5.
3. In BookingController.java, check and change maxDate in Line 126.

ADMIN LOGIN CREDENTIAL:
username: admin123
password: #Admin123
