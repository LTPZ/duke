public class Events extends Tasks {
    protected String at;

    public Events(String description, String type, String at) {
        super(description, type);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
