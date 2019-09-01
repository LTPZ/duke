import jdk.jfr.Event;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskList {
    protected String currCommand;
    protected List<Tasks> list;
    protected int size;

    public TaskList(int size) {
        this.size = size;
        this.list = new ArrayList<Tasks>();
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
                type = "events";
        }
        boolean isDone;
        char d = s.charAt(4);
        isDone = (d == '1' ? true : false);
        //kill the |
        String content = s.substring(s.indexOf(" | ") + 3);
        content = content.substring(s.indexOf(" |") + 3);
        String time = null;
        Date date = null;
        if (content.contains(" | ")) {
            //process as event or deadline
            time = content.substring(content.indexOf(" | ")+3);
            content = content.substring(0,content.indexOf(" | "));
            date = new SimpleDateFormat("dd/mm/yyyy").parse(time);
        }
        if (type == "todo") list.add(new Tasks(content, isDone, type));
        else if (type == "deadline") list.add(new Deadline(content, isDone, type, date));
        else list.add(new Events(content, isDone, type, date));
        size++;
    }

    public void scantoProcess() {
        Scanner in = new Scanner(System.in);
        currCommand = in.nextLine();
        try {
            if (check().equals("bye")) {
                //exit
                goodbye();
            } else if (check().equals("done")) {
                //done the task in the list
                doneTask();
                scantoProcess();
            } else if (check().equals("list")) {
                //list the task in the list
                listTask();
                scantoProcess();
            } else if (check().equals("todo")) {
                //add
                size = addToDo();
                scantoProcess();
            } else if (check().equals("deadline")) {
                //add
                size = addDDL();
                scantoProcess();
            } else if (check().equals("event")){
                //event
                size = addEvent();
                scantoProcess();
            } else {
                //find
                findKey();
                scantoProcess();
            }
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            scantoProcess();
        }
    }

    private String check() throws EntryException{
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        if (!command1.equals("bye") && !command1.equals("done") && !command1.equals("list")
                && !command1.equals("todo") && !command1.equals("deadline") && !command1.equals("event")
                && !command1.equals("find"))
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

    private void doneTask() {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        try {
            checkLackInfo();
            String numinString = tok.nextToken();
            int num = Integer.parseInt(numinString);
            int i;
            for (i = 0; i < num - 1; i++) ;
            list.get(i).done();
            System.out.println("\tNice! I've marked this task as done:");
            if (list.get(i).getType().equals("todo")) System.out.println("\t[T]" + list.get(i).toString());
            else System.out.println("\t" + list.get(i).toString());
            try {
                File currList = new File("D:/NUS/IDEs/Du/List.txt");
                //update the done information by reading and writing to a temp file
                BufferedReader reader = new BufferedReader(new FileReader(currList));
                File data = new File("D:/NUS/IDEs/Du/List2.txt");
                FileWriter newData = new FileWriter(data);
                PrintWriter pw = new PrintWriter(newData);
                String oldData = null;
                for (int j = 0; j < i; j++) {
                    oldData = reader.readLine();
                    pw.println(oldData);
                }
                oldData = reader.readLine();
                pw.println(oldData.substring(0,4) + "1" + oldData.substring(5));
                while ((oldData = reader.readLine()) != null) {
                    pw.println(oldData);
                }
                reader.close();
                pw.flush();
                pw.close();
                //print back
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
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I cannot done an empty task");
        }
    }

    private void listTask() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getType().equals("todo")) System.out.println("\t" + (i + 1) + ".[T]" + list.get(i).toString());
            else System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
        }
    }
    private void todofileWrite(Tasks ta) throws IOException {
        File data = new File("D:/NUS/IDEs/Du/List.txt");
        FileWriter newData = new FileWriter(data, true);
        PrintWriter pw = new PrintWriter(newData);
        pw.print("T | " + (ta.isDone()? 1:0) + " |" + ta.getDescription() + "\r\n");
        pw.flush();
        pw.close();
    }

    private void deadlinefileWrite(Deadline dd) throws IOException {
        File data = new File("D:/NUS/IDEs/Du/List.txt");
        FileWriter newData = new FileWriter(data, true);
        PrintWriter pw = new PrintWriter(newData);
        pw.print("D | " + (dd.isDone()? 1:0) + " |" + dd.getDescription() + " | " + dd.getTime() + "\r\n");
        pw.flush();
        pw.close();
    }
    private void eventfileWrite(Events ev) throws IOException {
        File data = new File("D:/NUS/IDEs/Du/List.txt");
        FileWriter newData = new FileWriter(data, true);
        PrintWriter pw = new PrintWriter(newData);
        pw.print("E | " + (ev.isDone()? 1:0) + " |" + ev.getDescription() + " | " + ev.getTime()+ "\r\n");
        pw.flush();
        pw.close();
    }

    private void findKey() {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String temp = tok.nextToken();
        try {
            //check if more information is needed
            checkLackInfo();
            String target = tok.nextToken();
            System.out.println("\tHere are the matching tasks in your list:");
            int getTime = 0;
            for (int i = 0; i < size; ++i) {
                if (list.get(i).getDescription().indexOf(target) != -1) {
                    getTime++;
                    if (list.get(i).getType().equals("todo")) {
                        System.out.println("\t" + getTime + ".[T]" + list.get(i).toString());
                    } else {
                        System.out.println("\t" + getTime + "." + list.get(i).toString());
                    }
                }
            }
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! I cannot find an empty string, specific string needed");
        }
    }

    private  int addToDo() {
        try {
            checkLackInfo();
            String content = currCommand.substring(currCommand.indexOf(" "));
            list.add(new Tasks(content, "todo"));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            todofileWrite(new Tasks(content, "todo"));
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        } finally {
            return size;
        }
    }

    private  int addDDL() {
        try {
            checkLackInfo();
            checkDeadlineTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /by"));
            String time = currCommand.substring(currCommand.indexOf(" /by") + 5);
            Date by = new SimpleDateFormat("dd/mm/yyyy").parse(time);
            list.add(new Deadline(content, false, "deadline", by, time));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            deadlinefileWrite(new Deadline(content,false, "deadline", by, time));
            size++;
        } catch (EntryException e) {
            System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (TimeException e) {
            System.out.println("\t☹ OOPS!!! The time of a deadline cannot be empty.");
        } finally {
            return size;
        }
    }
    private  int addEvent() {
        try {
            checkLackInfo();
            checkEventTime();
            String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /at"));
            String time = currCommand.substring(currCommand.indexOf(" /by") + 5);
            Date at = new SimpleDateFormat("dd/mm/yyyy").parse(time);
            list.add(new Events(content, false, "events", at, time));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + list.get(size).toString());
            System.out.println("\tNow you have " + (size + 1) + " tasks in the list.");
            eventfileWrite(new Events(content, false, "events", at, time));
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

class EntryException extends Exception {

}

class TimeException extends Exception {

}
