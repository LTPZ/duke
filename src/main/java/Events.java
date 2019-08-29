import java.util.Date;

public class Events extends Tasks {
    protected Date at;

    public Events(String description, String type, Date at) {
        super(description, type);
        this.at = at;
    }

    public Events(String description, boolean isDone, String type, Date at) {
        super(description, isDone, type);
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
