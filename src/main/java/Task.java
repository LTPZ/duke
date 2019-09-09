/**
 * The class for Task representation
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor
     *
     * @param description content of task
     * @param type task type
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructor
     *
     * @param description content of task
     * @param isDone is this task done
     * @param type task type
     */
    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * isDone getter
     *
     * @return if the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * type getter
     *
     * @return the type of the task
     */
    public String getType() {
        return type;
    }

    /**
     * description getter
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns string of the task.
     *
     * @return string of the task
     */
    public String toString() {
        if (isDone == true) return "[\u2713]" + description;
        else return "[\u2717]" + description;
    }

    /**
     * set isDone as done
     *
     */
    public void setDone() {
        this.isDone = true;
    }
}

