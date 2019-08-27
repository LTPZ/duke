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
        }
        else if (check().equals("done")) {
            //done the task in the list
            doneTask(currTask);
            scantoProcess(currTask, size);
        }
        else if (check().equals("list")) {
            //list the task in the list
            listTask(currTask, size);
            scantoProcess(currTask, size);
        }
        else {
            //add it to the list
            add(currTask, size);
            size++;
            scantoProcess(currTask, size);
        }
        //scan again
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
        System.out.println("\t[\u2713] " + currTask[i].getTask());
    }

    private void listTask(Tasks currTask[], int size) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            if (currTask[i].getDone()) System.out.println("\t" + (i + 1) + ".[\u2713] " + currTask[i].description);
            else System.out.println("\t" + (i + 1) + ".[\u2717] " + currTask[i].description);
        }
    }

    private  void add(Tasks currTask[], int size) {
        currTask[size] = new Tasks(currCommand);
        System.out.println("\tadded: " + currCommand);
    }

    private void goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
