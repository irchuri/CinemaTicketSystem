package cinema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a movie session with a title, start time, duration, and seating plan.
 */
public class Session {
    private String movieTitle;
    private LocalDateTime startTime;
    private int durationMinutes;
    private boolean[][] seats; // true - occupied, false - free

    public Session(String movieTitle, LocalDateTime startTime, int durationMinutes, int rows, int cols) {
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.seats = new boolean[rows][cols];
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isSeatAvailable(int row, int col) {
        return !seats[row][col];
    }

    public boolean bookSeat(int row, int col) {
        if (isSeatAvailable(row, col)) {
            seats[row][col] = true;
            return true;
        }
        return false;
    }

    public void printSeatingPlan() {
        System.out.println("Seating plan for session: " + movieTitle + " at " + 
            startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }

    public boolean hasAvailableSeats() {
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) return true;
            }
        }
        return false;
    }
}