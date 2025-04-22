package cinema;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hall in a cinema with a number, seating configuration, and sessions.
 */
public class Hall {
    private int hallNumber;
    private int rows;
    private int cols;
    private List<Session> sessions;

    public Hall(int hallNumber, int rows, int cols) {
        this.hallNumber = hallNumber;
        this.rows = rows;
        this.cols = cols;
        this.sessions = new ArrayList<>();
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}