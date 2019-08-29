import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Command {
    private String currCommand;

    public void scantoProcess(List<Tasks> currTask, int size) {
        Scanner in = new Scanner(System.in);
        currCommand = in.nextLine();
        try {
            if (check().equals("bye")) {
                //exit
                goodbye();
            } else if (check().equals("done")) {
                //done the task in the list
                doneTask(currTask);
                scantoProcess(currTask, size);
            } else if (check().equals("list")) {
                //list the task in the list
                listTask(currTask, size);
                scantoProcess(currTask, size);
            } else if (check().equals("todo")) {
                //add
                size = addToDo(currTask, size);
                scantoProcess(currTask, size);
            } else if (check().equals("deadline")) {
                //add
                size = addDDL(currTask, size);
                scantoProcess(currTask, size);
            } else {
                //event
                size = addEvent(currTask, size);
                scantoProcess(currTask, size);
            }
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            scantoProcess(currTask, size);
        }
    }

    private String check() throws EntryException{
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        if (!command1.equals("bye") && !command1.equals("done") && !command1.equals("list") && !command1.equals("todo") && !command1.equals("deadline") && !command1.equals("event"))
            throw new EntryException();
        return command1;
    }

    private void checkLackInfo() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String tmp = tok.nextToken();
        if (!tok.hasMoreElements()) {
            throw new EntryException();
        }
    }

    private void checkDeadlineTime() throws TimeException {
        if (currCommand.indexOf(" /by") == -1) throw new TimeException();
    }

    private void checkEventTime() throws TimeException {
        if (currCommand.indexOf(" /at") == -1) throw new TimeException();
    }

    private void doneTask(List<Tasks> currTask) {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        try {
            checkLackInfo();
            String numinString = tok.nextToken();
            int num = Integer.parseInt(numinString);
            int i;
            for (i = 0; i < num - 1; i++) ;
            currTask.get(i).done();
            System.out.println("\tNice! I've marked this task as done:");
            if (currTask.get(i).getType().equals("todo")) System.out.println("\t[T]" + currTask.get(i).toString());
            else System.out.println("\t" + currTask.get(i).toString());
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I cannot done an empty task");
        }
    }

    private void listTask(List<Tasks> currTask, int size) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            if (currTask.get(i).getType().equals("todo")) System.out.println("\t" + (i + 1) + ".[T]" + currTask.get(i).toString());
            else System.out.println("\t" + (i + 1) + "." + currTask.get(i).toString());
        }
    }

    private  int addToDo(List<Tasks> currTask, int size) {
        try {
            checkLackInfo();
            String content = currCommand.substring(currCommand.indexOf(" "));
            currTask.add(new Tasks(content, "todo"));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + currTask.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        } finally {
            return size;
        }
    }

    private  int addDDL(List<Tasks> currTask, int size) {
        try {
            checkLackInfo();
            checkDeadlineTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /by"));
            String by = currCommand.substring(currCommand.indexOf(" /by") + 5);
            currTask.add(new Deadline(content, "deadline", by));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + currTask.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (TimeException e) {
            System.out.println("\t☹ OOPS!!! The time of a deadline cannot be empty.");
        } finally {
            return size;
        }
    }
    private  int addEvent(List<Tasks> currTask, int size) {
        try {
            checkLackInfo();
            checkEventTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /at"));
            String at = currCommand.substring(currCommand.indexOf(" /at") + 5);
            currTask.add(new Events(content, "events", at));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + currTask.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
        } catch (TimeException e) {
            System.out.println("\t☹ OOPS!!! The time of an event cannot be empty.");
        } finally {
            return size;
        }
    }

    private void goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
