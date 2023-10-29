                                                                     Holiday Maker

Link to LucidChart : https://lucid.app/lucidchart/5e97e25e-b2f4-4f22-99c8-6f38ca40f586/edit?viewport_loc=-3819%2C-2729%2C5713%2C2770%2C0_0&invitationId=inv_2afccd8c-221e-4669-b468-ea0d6ed2583b 



Daily documentation ( Tasks / Sessions)

Day: 1 (13/10) :
Interview with Benjamin about the project. Did basic planning about what menu options we can have and how the program should flow. Basic Lo-fi prototypes were created as a team with the team-mates present in the school.

Day:2 (16/10):
Team started planning Goals ( M1/M2/M3) and building UML diagrams ( using Lucidchart) and got a clearer picture of the menu system that we can have. Main challenge was to follow TDD. So the team decided to study a few hours about unit tests first.
Also, we set up the GitRepo and initiated the project.
Focus : Read and try out simpler unit testing and setting goals for M1/M2.
M1 Goal :  We have MVP for M1 . Our MVP had some very basic features like fetching a user from the database, getting all events(fetched from the db) and displaying them in our console. A functional menu system which can display the existing data from db.
M2 Goal: Our goal for M2 was to make a booking function available in the product and to add a new user in the Db. The travel agent can see the events, create a customer/user and do a booking by selecting some events for him.
M3 Goal: To add menu options like making the booking paid and getting all the details of a booking by bookingId.

Day:3 (17/10):
Setup database connection with the java project. Pushed on github the connection class. Tried to get the data fetched from the database in the java console. Team helped everyone to get the setup done with Datagrip + Intellij  etc.

Day: 4 (18/10):
Created database. Tables like users, events, rooms,packages, bookings and some join tables for many-to-many relationships between the tables like reservations and eventReservations etc.
Nisha: Created user and users class in java along with the simple methods to get users from db and their corresponding tests.
Zahra: Event and events classes + tests for all simple methods
Rasmus: Room and rooms classes + tests
Reine : Package and packages classes + tests
Mukhtar: Menu ( Main menu) , Menu options, menu system classes + tests

Day: 5 (19/10):
Today everyone was ready with their base classes and some methods and tests. Tested everyone’s classes and merged them in the project to get all the base classes together. MVP reached and the project has a main menu which has menu options. One can click a menu Option to and events from the database are visible based on start and end dates entered by the user.

Day: 6 (20/10):
Tasks for today (pair wise) :
Nisha , Rasmus: Create your own trip feature.
Mukhtar : Delete a booking by bookingId feature.
Zahra : Create a new user feature.
Reine: Completed some packages and tests for it. ( sick)

Day: 7 (23/10):
Zahra completed creating a new user feature. Package and packages and tests completed with search by type, name etc. Creating your own trip has reached multiple event selection step. BookingProcess class created with some methods and their tests .

Day: 8 ( 24/10):
Booking process is yet to be completed to create your own trip feature. Everyone sat in the team's meeting and worked together to get the feature working. Did calculation of price, rooms selection based on capacity of rooms etc.
Completed booking of a trip chosen by the user.
Mukhtar & Rasmus also completed changing paid / unpaid status of the booking.

Day: 9 (25/10):
Refactoring of code done by Mukhtar and where the whole team contributed. New classes like BookingService, UserInteraction, BookingProcess and EventBookingProcess were created and the methods and corresponding tests were segregated based on their usage and type. Completed the refactoring and got all tests working correctly.

Day: 10 (26/10):
Presented the Project before the class and did a retrospective.
