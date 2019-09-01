import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class TaskList {
    protected String currCommand;
    protected List<Task> list;
    protected int size;

    public TaskList(int size) {
        this.size = size;
        this.list = new ArrayList<>();
    }

    public void oldDataProcess(String s) throws ParseException {
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
        content = content.substring(s.indexOf(" |") + 3);
        String time;
        Date date = null;
        if (content.contains(" | ")) {
            //process as event or deadline
            time = content.substring(content.indexOf(" | ") + 3);
            content = content.substring(0, content.indexOf(" | "));
            date = new SimpleDateFormat("dd/MM/yyyy").parse(time);
        }
        if (type == "todo") list.add(new Task(content, isDone, type));
        else if (type == "deadline") list.add(new Deadline(content, isDone, type, date));
        else list.add(new Even(content, isDone, type, date));
        size++;
    }

    public void scanProcess() {
        Scanner in = new Scanner(System.in);
        currCommand = in.nextLine();
        try {
            if (check().equals("bye")) {
                //exit
                sayGoodbye();
            } else if (check().equals("delete")) {
                //delete the task
                deleteTask();
                scanProcess();
            } else if (check().equals("done")) {
                //done the task in the list
                doneTask();
                scanProcess();
            } else if (check().equals("list")) {
                //list the task in the list
                listTask();
                scanProcess();
            } else if (check().equals("todo")) {
                //add
                addToDo();
                scanProcess();
            } else if (check().equals("deadline")) {
                //add
                addDeadline();
                scanProcess();
            } else {
                //event
                addEvent();
                scanProcess();
            }
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            scanProcess();
        }
    }

    private String check() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        if (!command1.equals("bye") && !command1.equals("delete") && !command1.equals("done") && !command1.equals("list")
                && !command1.equals("todo") && !command1.equals("deadline") && !command1.equals("event"))
            throw new EntryException();
        return command1;
    }

    private void sayGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private void deleteTask() {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String temp = tok.nextToken();
        try {
            //check if more information is needed
            checkLackInfo();
            //search for the delete number
            String numText = tok.nextToken();
            int num = Integer.parseInt(numText) - 1;
            System.out.println("\tNoted. I've removed this task:");
            if (list.get(num).getType().equals("todo")) {
                //it is a common type
                System.out.println("\t[T]" + list.get(num).toString());
            } else {
                //deadline or event type
                System.out.println("\t" + list.get(num).toString());
            }
            //delete the one in list
            list.remove(num);
            System.out.println("\tNow you have " + (size - 1) + " tasks in the list.");
            //print the change in the file
            changeFile(num, "delete");
            size--;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I cannot delete an empty task, specific number needed");
        }
    }

    private void doneTask() {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String temp = tok.nextToken();
        try {
            //check if more information is needed
            checkLackInfo();
            //search for the done number
            String numText = tok.nextToken();
            int num = Integer.parseInt(numText) - 1;
            //done the one in list
            list.get(num).setDone();
            System.out.println("\tNice! I've marked this task as done:");
            if (list.get(num).getType().equals("todo")) {
                //it is a common type
                System.out.println("\t[T]" + list.get(num).toString());
            } else {
                //deadline or event type
                System.out.println("\t" + list.get(num).toString());
            }
            //print the change in the file
            changeFile(num, "done");
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I cannot delete an empty task, specific number needed");
        }
    }

    private void listTask() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getType().equals("todo"))
                System.out.println("\t" + (i + 1) + ".[T]" + list.get(i).toString());
            else System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
        }
    }

    private void addFile(Task currTask, String Type) throws IOException {
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

    private void addToDo() {
        try {
            checkLackInfo();
            String content = currCommand.substring(currCommand.indexOf(" "));
            Task currTask = new Task(content, "todo");
            list.add(currTask);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            addFile(currTask, "todo");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addDeadline() {
        try {
            checkLackInfo();
            checkDeadlineTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /by"));
            String time = currCommand.substring(currCommand.indexOf(" /by") + 5);
            Date by = new SimpleDateFormat("dd/MM/yyyy").parse(time);
            Deadline deadline = new Deadline(content, false, "deadline", by, time);
            list.add(deadline);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            addFile(deadline,"deadline");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (TimeException e) {
            System.out.println("\t☹ OOPS!!! The time of a deadline cannot be empty.");
        } catch (Exception e) {
            System.out.println("\t☹ OOPS!!! The time of a deadline should be in \"dd/mm/yyyy form\".");
        }
    }

    private void addEvent() {
        try {
            checkLackInfo();
            checkEventTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /at"));
            String time = currCommand.substring(currCommand.indexOf(" /at") + 5);
            Date at = new SimpleDateFormat("dd/MM/yyyy").parse(time);
            Even event = new Even(content, false, "event", at, time);
            list.add(event);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            addFile(event,"Event");
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
        } catch (TimeException e) {
            System.out.println("\t☹ OOPS!!! The time of an event cannot be empty.");
        } catch (Exception e) {
            System.out.println("\t☹ OOPS!!! The time of a event should be in \"dd/mm/yyyy form\".");
        }
    }

    private void changeFile(int changedIndex, String Type) {
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
            oldData = null;
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

    private void checkLackInfo() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String tmp = tok.nextToken();
        if (!tok.hasMoreElements()) {
            throw new EntryException();
        }
    }

    private void checkDeadlineTime() throws Exception {
        //check if there is no time
        if (currCommand.indexOf(" /by") == -1) {
            throw new TimeException();
        }
    }

    private void checkEventTime() throws Exception {
        //check if there is no time
        if (currCommand.indexOf(" /at") == -1) {
            throw new TimeException();
        }
    }

    class TimeException extends Exception {
    }

    class EntryException extends Exception {
    }
}