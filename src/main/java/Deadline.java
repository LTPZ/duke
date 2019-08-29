public class Deadline extends Tasks {
    protected String by;

    public Deadline(String description, String type, String by) {
        super(description, type);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
