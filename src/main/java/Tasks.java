public class Tasks {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Tasks() {

    }

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Tasks(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void done(){
        this.isDone = true;
    }
}

