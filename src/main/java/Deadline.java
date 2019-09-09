import java.util.Date;

/**
 * The class for deadline representation
 */
public class Deadline extends Task {
    protected Date by;
    protected String time;

    /**
     * Constructor
     *
     * @param description content of deadline
     * @param isDone is this deadline done
     * @param type deadline type
     */
    public Deadline(String description, boolean isDone, String type, Date by) {
        super(description, type);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Constructor
     *
     * @param description content of deadline
     * @param isDone is this deadline done
     * @param type deadline type
     * @param time the time of deadline
     */
    public Deadline(String description, boolean isDone, String type, Date by, String time) {
        super(description, isDone, type);
        this.by = by;
        this.time = time;
    }

    /**
     * Returns the time of the deadline
     *
     * @return deadline time
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns string of the deadline.
     *
     * @return string of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
