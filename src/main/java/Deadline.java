import java.util.Date;

public class Deadline extends Tasks {
    protected Date by;

    public Deadline(String description, String type, Date by) {
        super(description, type);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String type, Date by) {
        super(description, isDone, type);
        this.by = by;
    }

    public Date getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
