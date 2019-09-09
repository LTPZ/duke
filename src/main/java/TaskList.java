import java.util.*;

/**
 * The class to represent the current task list
 */
public class TaskList {
    protected List<Task> list;
    protected int size;

    public TaskList(List<Task> list) {
        this.list = list;
        this.size = list.size();
    }

    /**
     * List getter
     *
     * @return the task list
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Size getter
     *
     * @return the task size
     */
    public int getSize() {
        return size;
    }

    /**
     * Delete one element from the task list
     *
     * @param num the position of the element
     * @return the deleted task
     */
    public Task deleteTask(int num) {
        Task temp = list.get(num);
        list.remove(num);
        size--;
        return temp;
    }

    /**
     * Done the task in the list
     *
     * @return the task list
     */
    public Task doneTask(int num) {
        list.get(num).setDone();
        Task temp = list.get(num);
        return temp;
    }

    /**
     * Add todo type task to the list
     *
     * @param t the task to add
     */
    public void addToDo(Task t) {
        list.add(t);
        size++;
    }

    /**
     * Add deadline type task to the list
     *
     * @param d the task to add
     */
    public void addDeadline(Deadline d) {
        list.add(d);
        size++;
    }

    /**
     * Add event type task to the list
     *
     * @param e the task to add
     */
    public void addEvent(Even e) {
        list.add(e);
        size++;
    }

    /**
     * Search for the key in the list
     *
     * @param key the key to search in the list
     */
    public void findKey(String key) {
        int getTime = 0;
        Ui keyUi = new Ui();
        keyUi.printKey1();
        for (int i = 0; i < size; ++i) {
            if (list.get(i).getDescription().indexOf(key) != -1) {
                getTime++;
                keyUi.printKey2(getTime, list.get(i).getType(), list.get(i).toString());
            }
        }
    }
}