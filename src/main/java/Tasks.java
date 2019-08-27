public class Tasks {
    protected String description;
    protected boolean isDone;

    public Tasks() {

    }

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }


    public void done(){
        this.isDone = true;
    }
}

