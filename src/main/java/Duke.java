import java.util.Scanner;

class Line {
    String CurrLine;
    void Enter() {
        Scanner in = new Scanner(System.in);
        CurrLine = in.nextLine();
    }
    boolean Judge() {
        if (CurrLine.equals("bye")) return true;
        else return false;
    }
    void Echo() {
        System.out.println("\t" + CurrLine);
    }
    void Goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}

public class Duke {
    public static void main(String args[]) {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Line OneLine = new Line();
        //enter
        OneLine.Enter();
        //test this line
        while (OneLine.Judge() == false) {
            OneLine.Echo();
            OneLine.Enter();
        }
        //goodbye
        OneLine.Goodbye();
    }
}
