import java.util.Scanner;

class Line {
    private String currLine;
    void Enter() {
        Scanner in = new Scanner(System.in);
        currLine = in.nextLine();
    }
    boolean Judge() {
        if (currLine.equals("bye")) return true;
        else return false;
    }
    void Echo() {
        System.out.println("\t" + currLine);
    }
    void Goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}

public class Duke {
    public static void main(String args[]) {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Line oneLine = new Line();
        //enter
        oneLine.Enter();
        //test this line
        while (oneLine.Judge() == false) {
            oneLine.Echo();
            oneLine.Enter();
        }
        //goodbye
        oneLine.Goodbye();
    }
}
