import java.util.Scanner;

public class Duke {
    public static void main(String args[]) {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String temp = null;
        do {
            temp = in.nextLine();
            System.out.println("\t" + temp);
        } while(!temp.equals("bye"));
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
