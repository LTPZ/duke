import java.util.Date;

/**
 * The class for event representation
 */
public class Even extends Task {
    protected Date at;
    protected String time;

    /**
     * Constructor
     *
     * @param description content of event
     * @param isDone is this event done
     * @param type event type
     */
    public Even(String description, boolean isDone, String type, Date at) {
        super(description, type);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Constructor
     *
     * @param description content of event
     * @param isDone is this event done
     * @param type event type
     * @param time the time of event
     */
    public Even(String description, boolean isDone, String type, Date at, String time) {
        super(description, isDone, type);
        this.at = at;
        this.time = time;
    }

    /**
     * Returns the time of the event
     *
     * @return event time
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns string of the event.
     *
     * @return string of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
