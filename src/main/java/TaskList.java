import java.util.*;

public class TaskList {
    protected List<Task> list;
    protected int size;

    public TaskList(List<Task> list) {
        this.list = list;
        this.size = list.size();
    }

    public List<Task> getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    public Task deleteTask(int num) {
        Task temp = list.get(num);
        list.remove(num);
        size--;
        return temp;
    }

    public Task doneTask(int num) {
        list.get(num).setDone();
        Task temp = list.get(num);
        return temp;
    }

    public void addToDo(Task t) {
        list.add(t);
        size++;
    }

    public void addDeadline(Deadline d) {
        list.add(d);
        size++;
    }


    public void addEvent(Even e) {
        list.add(e);
        size++;
    }


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