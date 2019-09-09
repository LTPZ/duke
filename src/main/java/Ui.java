import java.util.List;
import java.util.Scanner;

/**
 * The user interface
 */
public class Ui {
    protected String currCommand;

    /**
     * CurrCommand getter
     *
     * @return currCommand
     */
    public String getCurrCommand() {
        return currCommand;
    }

    /**
     * Read the next command from the command line
     */
    public void readCommand() {
        Scanner in = new Scanner(System.in);
        currCommand = in.nextLine();
    }

    /**
     * Print goodbye
     */
    public void printGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Print successfully removed tasks
     *
     * @param type the type of the removed task
     * @param content the content of the removed task
     * @param size the size of remained list
     */
    public void printRemove(String type, String content, int size) {
        System.out.println("\tNoted. I've removed this task:");
        if (type.equals("todo")) {
            System.out.println("\t[T]" + content);
        } else {
            System.out.println("\t" + content);
        }
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Print the task is done
     *
     * @param type the type of the removed task
     * @param content the content of the removed task
     * @param size the size of remained list
     */
    public void printDone(String type, String content, int size) {
        System.out.println("\tNice! I've marked this task as done:");
        if (type.equals("todo")) {
            //it is a common type
            System.out.println("\t[T]" + content);
        } else {
            //deadline or event type
            System.out.println("\t" + content);
        }
    }

    /**
     * Print the current list
     *
     * @param list the current list
     */
    public void printList(List<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("todo"))
                System.out.println("\t" + (i + 1) + ".[T]" + list.get(i).toString());
            else System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Print successfully added tasks
     *
     * @param content the content of the removed task
     * @param size the size of remained list
     */
    public void printAdd(String content, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + content);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Print matching sentence
     */
    public void printKey1() {
        System.out.println("\tHere are the matching tasks in your list:");
    }

    /**
     * Print found keys
     *
     * @param getTime the num of the task
     * @param type the type of the removed task
     * @param content the content of the removed task
     */
    public void printKey2(int getTime, String type, String content) {
        if (type.equals("todo")) {
            System.out.println("\t" + getTime + ".[T]" + content);
        } else {
            System.out.println("\t" + getTime + "." + content);
        }
    }

    /**
     * Print exceptions
     *
     * @param type the type of exceptions
     */
    public void printException(String type) {
        if (type.equals("random")) {
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (type.equals("delete")) {
            System.out.println("\t☹ OOPS!!! I cannot delete an empty task, specific number needed");
        } else if (type.equals("done")) {
            System.out.println("\t☹ OOPS!!! I cannot done an empty task, specific number needed");
        } else if (type.equals("todo")) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (type.equals("deadlineD")) {
            System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (type.equals("deadlineT")) {
            System.out.println("\t☹ OOPS!!! The time of a deadline cannot be empty.");
        } else if (type.equals("deadlineTF")) {
            System.out.println("\t☹ OOPS!!! The time of a deadline should be in \"dd/mm/yyyy form\".");
        } else if (type.equals("eventE")) {
            System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
        } else if (type.equals("eventT")) {
            System.out.println("\t☹ OOPS!!! The time of an event cannot be empty.");
        } else if (type.equals("eventTF")) {
            System.out.println("\t☹ OOPS!!! The time of a event should be in \"dd/mm/yyyy form\".");
        } else if (type.equals("key")) {
            System.out.println("\t☹ OOPS!!! I cannot find an empty string, specific string needed");
        }
    }
}
