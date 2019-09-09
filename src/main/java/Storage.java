import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class to deal with storage problems
 */
public class Storage {
    protected List<Task> list;

    /**
     * Constructor
     */
    public Storage() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the task list in the storage
     *
     * @return The task list in the storage.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * process the task list from the list
     *
     * @param s the task line in the list
     * @throws ParseException
     */
    private void processFile(String s) throws ParseException {
        //process the old data
        char t = s.charAt(0);
        String type;
        switch (t) {
            case 'T':
                type = "todo";
                break;
            case 'D':
                type = "deadline";
                break;
            default:
                type = "event";
        }
        boolean isDone;
        char d = s.charAt(4);
        isDone = (d == '1' ? true : false);
        //kill the |
        String content = s.substring(s.indexOf(" | ") + 3);
        content = content.substring(s.indexOf(" |") + 2);
        String time;
        Date date = null;
        if (content.contains(" | ")) {
            //process as event or deadline
            time = content.substring(content.indexOf(" | ") + 3);
            content = content.substring(0, content.indexOf(" | "));
            date = new SimpleDateFormat("dd/MM/yyyy").parse(time);
        }
        if (type.equals("todo")) list.add(new Task(content, isDone, type));
        else if (type.equals("deadline")) list.add(new Deadline(content, isDone, type, date));
        else list.add(new Even(content, isDone, type, date));
    }

    /**
     * Load the task list file
     *
     * @throws IOException
     * @throws ParseException
     */
    public void readFile() throws IOException, ParseException {
        File list = new File("D:/NUS/IDEs/Du/List.txt");
        BufferedReader reader = new BufferedReader(new FileReader(list));
        String oldData;
        while ((oldData = reader.readLine()) != null) {
            //process old data
            processFile(oldData);
        }
        reader.close();
    }

    /**
     * Print the new tasks in the file
     *
     * @param currTask the new task
     * @param Type the type of the task
     * @throws IOException
     */
    public void addFile(Task currTask, String Type) throws IOException {
        File data = new File("D:/NUS/IDEs/Du/List.txt");
        FileWriter newData = new FileWriter(data, true);
        PrintWriter pw = new PrintWriter(newData);
        if (Type.equals("todo")) {
            pw.print("T | " + (currTask.isDone() ? 1 : 0) + " |" + currTask.getDescription() + "\r\n");
        } else if (Type.equals("deadline")) {
            Deadline deadline = (Deadline) currTask;
            pw.print("D | " + (deadline.isDone() ? 1 : 0) + " |" + deadline.getDescription() + " | " + deadline.getTime() + "\r\n");
        } else {
            //event
            Even event = (Even) currTask;
            pw.print("E | " + (event.isDone() ? 1 : 0) + " |" + event.getDescription() + " | " + event.getTime() + "\r\n");
        }
        pw.flush();
        pw.close();
    }

    /**
     * Delete or done tasks in the file
     *
     * @param changedIndex the index of the changed task
     * @param Type the type of the task
     */
    public void changeFile(int changedIndex, String Type) {
        //this is used to delete or done tasks and print them in the file
        try {
            File currList = new File("D:/NUS/IDEs/Du/List.txt");
            //copy list 1 to temp list 2 and make the changes
            BufferedReader reader = new BufferedReader(new FileReader(currList));
            File data = new File("D:/NUS/IDEs/Du/List2.txt");
            FileWriter newData = new FileWriter(data);
            PrintWriter pw = new PrintWriter(newData);
            String oldData;
            for (int j = 0; j < changedIndex; j++) {
                oldData = reader.readLine();
                pw.println(oldData);
            }
            oldData = reader.readLine();
            //make the change
            if (Type == "done") {
                //change the done part to 1
                pw.println(oldData.substring(0, 4) + "1" + oldData.substring(5));
            } else if (Type == "delete") {
                //do not print this line so this line will appear "deleted" in the list
            }
            //continue copy the remaining items
            while ((oldData = reader.readLine()) != null) {
                pw.println(oldData);
            }
            reader.close();
            pw.flush();
            pw.close();
            //copy list 2 contents to list 1
            currList = new File("D:/NUS/IDEs/Du/List2.txt");
            reader = new BufferedReader(new FileReader(currList));
            data = new File("D:/NUS/IDEs/Du/List.txt");
            newData = new FileWriter(data);
            pw = new PrintWriter(newData);
            while ((oldData = reader.readLine()) != null) {
                //copy everything back
                pw.println(oldData);
            }
            reader.close();
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}