import java.util.Date;

public class Deadline extends Tasks {
    protected Date by;
    protected String time;

    public Deadline(String description, boolean isDone, String type, Date by) {
        super(description, type);
        this.by = by;
        this.isDone = isDone;
    }

    public Deadline(String description, boolean isDone, String type, Date by, String time) {
        super(description, isDone, type);
        this.by = by;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Date getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
