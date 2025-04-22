package cinema;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cinema with a name and a list of halls.
 */
public class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }
}