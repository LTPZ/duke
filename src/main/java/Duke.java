
public class Duke {
    public static void main(String args[]) {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Tasks[] lists = new Tasks[100];
        Command userEnter = new Command();
        userEnter.scantoProcess(lists, 0);
    }
}