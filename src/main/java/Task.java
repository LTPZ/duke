public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }
    public boolean isDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        if (isDone == true) return "[\u2713]" + description;
        else return "[\u2717]" + description;
    }

    public void setDone() {
        this.isDone = true;
    }
}

