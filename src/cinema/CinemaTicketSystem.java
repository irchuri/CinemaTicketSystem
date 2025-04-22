package cinema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Main class for the cinema ticket system, providing a console-based interface
 * for administrators and users to manage cinemas, halls, sessions, and tickets.
 */
public class CinemaTicketSystem {
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDateTime CURRENT_TIME = LocalDateTime.of(2025, 4, 22, 10, 0);

    public static void main(String[] args) {
        // Initialize users
        users.add(new User("admin", "admin123", true));
        users.add(new User("user", "user123", false));

        // Initialize test data
        initializeTestData();

        while (true) {
            System.out.println("\n=== Cinema Ticket System ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an action: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                User user = authenticateUser();
                if (user != null) {
                    if (user.isAdmin()) {
                        adminMenu();
                    } else {
                        userMenu();
                    }
                } else {
                    System.out.println("Invalid login or password!");
                }
            } else if (choice.equals("2")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static User authenticateUser() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.authenticate(login, password)) {
                return user;
            }
        }
        return null;
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add cinema");
            System.out.println("2. Add hall to cinema");
            System.out.println("3. Create session");
            System.out.println("4. View all cinemas and sessions");
            System.out.println("5. Exit");
            System.out.print("Choose an action: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addCinema();
            } else if (choice.equals("2")) {
                addHall();
            } else if (choice.equals("3")) {
                addSession();
            } else if (choice.equals("4")) {
                printAllCinemas();
            } else if (choice.equals("5")) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Find next session");
            System.out.println("2. Buy ticket");
            System.out.println("3. View seating plan");
            System.out.println("4. Exit");
            System.out.print("Choose an action: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                findNextSession();
            } else if (choice.equals("2")) {
                buyTicket();
            } else if (choice.equals("3")) {
                viewSeatingPlan();
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeTestData() {
        Cinema cinema1 = new Cinema("CinemaMax");
        Hall hall1 = new Hall(1, 5, 5);
        Hall hall2 = new Hall(2, 3, 4);
        cinema1.addHall(hall1);
        cinema1.addHall(hall2);

        hall1.addSession(new Session("Dune 2", LocalDateTime.of(2025, 4, 22, 12, 0), 150, 5, 5));
        hall1.addSession(new Session("Avatar 3", LocalDateTime.of(2025, 4, 22, 15, 0), 180, 5, 5));
        hall2.addSession(new Session("Dune 2", LocalDateTime.of(2025, 4, 22, 13, 0), 150, 3, 4));

        cinemas.add(cinema1);
    }

    private static void addCinema() {
        System.out.print("Enter cinema name: ");
        String name = scanner.nextLine();
        cinemas.add(new Cinema(name));
        System.out.println("Cinema " + name + " added.");
    }

    private static void addHall() {
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinema(cinemaName);
        if (cinema == null) {
            System.out.println("Cinema not found!");
            return;
        }

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of rows: ");
        int rows = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of seats per row: ");
        int cols = Integer.parseInt(scanner.nextLine());

        cinema.addHall(new Hall(hallNumber, rows, cols));
        System.out.println("Hall " + hallNumber + " added to cinema " + cinemaName);
    }

    private static void addSession() {
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinema(cinemaName);
        if (cinema == null) {
            System.out.println("Cinema not found!");
            return;
        }

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        Hall hall = findHall(cinema, hallNumber);
        if (hall == null) {
            System.out.println("Hall not found!");
            return;
        }

        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
        String timeStr = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.parse(timeStr, formatter);
        System.out.print("Enter duration (in minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());

        hall.addSession(new Session(movieTitle, startTime, duration, hall.getRows(), hall.getCols()));
        System.out.println("Session added.");
    }

    private static void printAllCinemas() {
        for (Cinema cinema : cinemas) {
            System.out.println("Cinema: " + cinema.getName());
            for (Hall hall : cinema.getHalls()) {
                System.out.println("  Hall " + hall.getHallNumber() + " (" + hall.getRows() + "x" + hall.getCols() + ")");
                for (Session session : hall.getSessions()) {
                    System.out.println("    Session: " + session.getMovieTitle() + " at " + 
                        session.getStartTime().format(formatter) + ", duration: " + 
                        session.getDurationMinutes() + " min");
                }
            }
        }
    }

    private static void findNextSession() {
        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();

        Session earliestSession = null;
        Cinema earliestCinema = null;
        Hall earliestHall = null;

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.getHalls()) {
                for (Session session : hall.getSessions()) {
                    if (session.getMovieTitle().equalsIgnoreCase(movieTitle) &&
                        session.getStartTime().isAfter(CURRENT_TIME) &&
                        session.hasAvailableSeats()) {
                        if (earliestSession == null || 
                            session.getStartTime().isBefore(earliestSession.getStartTime())) {
                            earliestSession = session;
                            earliestCinema = cinema;
                            earliestHall = hall;
                        }
                    }
                }
            }
        }

        if (earliestSession != null) {
            System.out.println("Next session: " + earliestSession.getMovieTitle() + 
                " at " + earliestCinema.getName() + ", hall " + earliestHall.getHallNumber() + 
                ", time: " + earliestSession.getStartTime().format(formatter));
        } else {
            System.out.println("No sessions found.");
        }
    }

    private static void buyTicket() {
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinema(cinemaName);
        if (cinema == null) {
            System.out.println("Cinema not found!");
            return;
        }

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        Hall hall = findHall(cinema, hallNumber);
        if (hall == null) {
            System.out.println("Hall not found!");
            return;
        }

        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter session time (yyyy-MM-dd HH:mm): ");
        String timeStr = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.parse(timeStr, formatter);

        Session session = findSession(hall, movieTitle, startTime);
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        session.printSeatingPlan();
        System.out.print("Enter row number (1-" + hall.getRows() + "): ");
        int row = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("Enter seat number (1-" + hall.getCols() + "): ");
        int col = Integer.parseInt(scanner.nextLine()) - 1;

        if (row >= 0 && row < hall.getRows() && col >= 0 && col < hall.getCols()) {
            if (session.bookSeat(row, col)) {
                System.out.println("Ticket successfully booked!");
            } else {
                System.out.println("Seat is already taken!");
            }
        } else {
            System.out.println("Invalid row or seat number!");
        }
    }

    private static void viewSeatingPlan() {
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinema(cinemaName);
        if (cinema == null) {
            System.out.println("Cinema not found!");
            return;
        }

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        Hall hall = findHall(cinema, hallNumber);
        if (hall == null) {
            System.out.println("Hall not found!");
            return;
        }

        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter session time (yyyy-MM-dd HH:mm): ");
        String timeStr = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.parse(timeStr, formatter);

        Session session = findSession(hall, movieTitle, startTime);
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        session.printSeatingPlan();
    }

    private static Cinema findCinema(String name) {
        for (Cinema cinema : cinemas) {
            if (cinema.getName().equalsIgnoreCase(name)) {
                return cinema;
            }
        }
        return null;
    }

    private static Hall findHall(Cinema cinema, int hallNumber) {
        for (Hall hall : cinema.getHalls()) {
            if (hall.getHallNumber() == hallNumber) {
                return hall;
            }
        }
        return null;
    }

    private static Session findSession(Hall hall, String movieTitle, LocalDateTime startTime) {
        for (Session session : hall.getSessions()) {
            if (session.getMovieTitle().equalsIgnoreCase(movieTitle) &&
                session.getStartTime().equals(startTime)) {
                return session;
            }
        }
        return null;
    }
}