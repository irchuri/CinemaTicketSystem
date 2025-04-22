# Cinema Ticket System

## Overview

The **Cinema Ticket System** is a console-based Java application designed to manage ticket bookings for a network of cinemas. This project was developed as part of Laboratory Work #3 to demonstrate object-oriented programming principles, including encapsulation, modularity, and user role-based access control. The application supports two user roles: administrators, who can manage cinemas, halls, and movie sessions, and regular users, who can search for sessions, book tickets, and view seating plans.

The system is implemented using pure Java (JDK 8 or higher) with no external dependencies, making it lightweight and easy to run. Data is stored in memory during runtime, and the application includes predefined test data for demonstration purposes.

## Features

- **User Authentication**: Supports admin (`admin/admin123`) and user (`user/user123`) accounts with role-based access.
- **Admin Functionality**:
  - Add new cinemas, halls, and movie sessions.
  - View all cinemas, halls, and sessions.
- **User Functionality**:
  - Search for the next available movie session by title.
  - Book tickets by selecting a cinema, hall, session, and seat.
  - View seating plans for specific sessions.
- **Error Handling**: Robust input validation for user inputs, such as invalid seat numbers or session times.
- **Test Data**: Preconfigured cinema with two halls and three movie sessions for immediate testing.

## Project Structure

The project is organized as follows:

```
CinemaTicketSystem/
├── src/
│   ├── cinema/
│   │   ├── Cinema.java          # Represents a cinema with a name and list of halls
│   │   ├── Hall.java            # Represents a hall with a number, seating configuration, and sessions
│   │   ├── Session.java         # Represents a movie session with title, start time, duration, and seats
│   │   ├── User.java            # Represents a user with login, password, and admin status
│   │   └── CinemaTicketSystem.java  # Main class with console interface
└── README.md                    # Project documentation
```

- All classes are part of the `cinema` package.
- Each class is defined in a separate `.java` file, adhering to Java naming conventions.

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Optional**: An IDE like IntelliJ IDEA, Eclipse, or VS Code for easier development and debugging.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-username/cinema-ticket-system.git
   cd cinema-ticket-system
   ```

2. **Verify Project Structure**: Ensure all `.java` files are in the `src/cinema` directory.

3. **Install JDK** (if not already installed):

   - **Linux**: `sudo apt install openjdk-17-jdk`
   - **Windows/Mac**: Download from Oracle or AdoptOpenJDK.

## How to Run

### Via Terminal

1. Navigate to the `src` directory:

   ```bash
   cd src
   ```

2. Compile the Java files:

   ```bash
   javac cinema/*.java
   ```

3. Run the application:

   ```bash
   java cinema.CinemaTicketSystem
   ```

### Via IDE

1. Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
2. Ensure `src` is marked as the source root:
   - **IntelliJ**: Right-click `src` → `Mark Directory as` → `Sources Root`.
   - **Eclipse**: Add `src` to `Java Build Path` in project properties.
   - **VS Code**: Ensure `.vscode/settings.json` includes `"java.project.sourcePaths": ["src"]`.
3. Run `CinemaTicketSystem.java`:
   - Right-click `CinemaTicketSystem.java` → `Run` or `Run Java`.

## Usage

1. **Start the Application**: Upon running, you will see the main menu:

   ```
   === Cinema Ticket System ===
   1. Login
   2. Exit
   Choose an action:
   ```

2. **Login**:

   - **Admin**: `admin` / `admin123`
   - **User**: `user` / `user123`

3. **Admin Menu**:

   - Add cinemas, halls, or sessions.

   - View all cinemas and their sessions.

   - Example:

     ```
     === Admin Menu ===
     1. Add cinema
     2. Add hall to cinema
     3. Create session
     4. View all cinemas and sessions
     5. Exit
     ```

4. **User Menu**:

   - Find the next session for a movie.

   - Book a ticket by selecting a cinema, hall, session, and seat.

   - View the seating plan.

   - Example:

     ```
     === User Menu ===
     1. Find next session
     2. Buy ticket
     3. View seating plan
     4. Exit
     ```

5. **Example Interaction**:

   - Find a session:

     ```
     Enter movie title: Dune 2
     Next session: Dune 2 at CinemaMax, hall 1, time: 2025-04-22 12:00
     ```

   - Book a ticket:

     ```
     Enter cinema name: CinemaMax
     Enter hall number: 1
     Enter movie title: Dune 2
     Enter session time (yyyy-MM-dd HH:mm): 2025-04-22 12:00
     Seating plan for session: Dune 2 at 2025-04-22 12:00
     [O] [O] [O] [O] [O]
     [O] [O] [O] [O] [O]
     [O] [O] [O] [O] [O]
     [O] [O] [O] [O] [O]
     [O] [O] [O] [O] [O]
     Enter row number (1-5): 1
     Enter seat number (1-5): 1
     Ticket successfully booked!
     ```

## Notes

- **Data Persistence**: Data is stored in memory and reset on application restart.
- **Time Handling**: The application uses a fixed date (`2025-04-22 10:00`) for session scheduling.
- **Error Handling**: Includes validation for invalid inputs, such as incorrect session times or occupied seats.
- **Extensibility**: The code is modular and can be extended with features like file-based persistence or a GUI.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature`.
3. Make your changes and commit: `git commit -m "Add your feature"`.
4. Push to the branch: `git push origin feature/your-feature`.
5. Open a Pull Request.

Please ensure your code follows Java coding standards and includes appropriate comments.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback, feel free to open an issue on GitHub or contact the maintainer at your-email@example.com.
