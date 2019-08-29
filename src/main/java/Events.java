import java.util.Date;

public class Events extends Tasks {
    protected Date at;
    protected String time;

    public Events(String description, boolean isDone, String type, Date at) {
        super(description, type);
        this.at = at;
        this.isDone = isDone;
    }

    public Events(String description, boolean isDone, String type, Date at, String time) {
        super(description, isDone, type);
        this.at = at;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
