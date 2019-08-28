public class Duke {
    public static void main(String args[]) {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Tasks[] lists = new Tasks[100];
        Command userEnter = new Command();
        userEnter.scantoProcess(lists, 0);
    }
}