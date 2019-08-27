import jdk.jfr.Event;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Command {
    private String currCommand;

    public void scantoProcess(Tasks currTask[], int size) {
        Scanner in = new Scanner(System.in);
        currCommand = in.nextLine();
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
            addToDo(currTask, size);
            size++;
            scantoProcess(currTask, size);
        } else if (check().equals("deadline")) {
            //add
            addDDL(currTask, size);
            size++;
            scantoProcess(currTask, size);
        } else {
            //event
            addEvent(currTask, size);
            size++;
            scantoProcess(currTask, size);
        }
    }

    private String check() {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        return command1;
    }

    private void doneTask(Tasks currTask[]) {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command1 = tok.nextToken();
        String numinString = tok.nextToken();
        int num = Integer.parseInt(numinString);
        int i;
        for (i = 0; i < num - 1; i++);
        currTask[i].done();
        System.out.println("\tNice! I've marked this task as done:");
        if (currTask[i].getType().equals("todo")) System.out.println("\t[T]" + currTask[i].toString());
        else System.out.println("\t" + currTask[i].toString());
    }

    private void listTask(Tasks currTask[], int size) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            if (currTask[i].getType().equals("todo")) System.out.println("\t" + (i + 1) + ".[T]" + currTask[i].toString());
            else System.out.println("\t" + (i + 1) + "." + currTask[i].toString());
        }
    }

    private  void addToDo(Tasks currTask[], int size) {
        String content = currCommand.substring(currCommand.indexOf(" "));
        currTask[size] = new Tasks(content);
        currTask[size].setType("todo");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + currTask[size].toString());
        System.out.println("\tNow you have "+ (size + 1) + " tasks in the list.");
    }

    private  void addDDL(Tasks currTask[], int size) {
        String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /by"));
        String by = currCommand.substring(currCommand.indexOf(" /by") + 5);
        currTask[size] = new Deadline(content, by);
        currTask[size].setType("deadline");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + currTask[size].toString());
        System.out.println("\tNow you have "+ (size + 1) + " tasks in the list.");
    }
    private  void addEvent(Tasks currTask[], int size) {
        String content = currCommand.substring(currCommand.indexOf(" "), currCommand.indexOf(" /at"));
        String at = currCommand.substring(currCommand.indexOf(" /at") + 5);
        currTask[size] = new Events(content, at);
        currTask[size].setType("event");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + currTask[size].toString());
        System.out.println("\tNow you have "+ (size + 1) + " tasks in the list.");
    }

    private void goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
