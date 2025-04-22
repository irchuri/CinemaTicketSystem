Cinema Ticket System
This is a console-based Java application for managing a cinema ticket system, as specified in Laboratory Work #3. It provides functionality for administrators to manage cinemas, halls, and sessions, and for users to find sessions, buy tickets, and view seating plans.
Project Structure

src/cinema/: Contains classes for the cinema ticket system.
Cinema.java: Represents a cinema with a name and list of halls.
Hall.java: Represents a hall with a number, seating configuration, and sessions.
Session.java: Represents a movie session with title, start time, duration, and seats.
User.java: Represents a user with login, password, and admin status.
CinemaTicketSystem.java: Main class with console interface.



Prerequisites

Java Development Kit (JDK) 8 or higher

How to Run

Navigate to the src directory:
cd src


Compile the Java files:
javac cinema/*.java


Run the application:
java cinema.CinemaTicketSystem



Usage

Login Credentials:
Admin: admin / admin123
User: user / user123


Admin Features:
Add cinemas, halls, and sessions.
View all cinemas and sessions.


User Features:
Find the next available session for a movie.
Buy tickets by selecting a cinema, hall, session, and seat.
View the seating plan for a session.



Notes

The application uses a fixed date (2025-04-22 10:00) for session scheduling.
Data is stored in memory and not persisted.
Error handling is implemented for invalid inputs.

