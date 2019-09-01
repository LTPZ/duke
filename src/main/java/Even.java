import java.util.Date;

public class Even extends Task {
    protected Date at;
    protected String time;

    public Even(String description, boolean isDone, String type, Date at) {
        super(description, type);
        this.at = at;
        this.isDone = isDone;
    }

    public Even(String description, boolean isDone, String type, Date at, String time) {
        super(description, isDone, type);
        this.at = at;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
