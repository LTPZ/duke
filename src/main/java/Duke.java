import java.util.Scanner;

class Lines {
    private String currLine;
    private String[] theList = new String[100];
    private int num = 0;
    void Numincrease() {
        num++;
    }
    void Add() {
        theList[num] = currLine;
        Numincrease();
    }
    void Enter() {
        Scanner in = new Scanner(System.in);
        currLine = in.nextLine();
        if (!currLine.equals("list")) Add();
    }
    void Showlist() {
        for (int i = 0; i < num; ++i) {
            System.out.println("\t" + (i+1) + ". " + theList[i]);
        }
    }
    boolean process() {
        if (currLine.equals("bye")) return true;
        else if (currLine.equals("list")) Showlist();
        else Echo();
        return false;
    }
    void Echo() {
        System.out.println("\tadded: " + currLine);
    }
    void Goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}

public class Duke {
    public static void main(String args[]) {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        Lines oneLine = new Lines();
        //enter
        oneLine.Enter();
        //test this line
        while (!oneLine.process()) {
            oneLine.Enter();
        }
        //goodbye
        oneLine.Goodbye();
    }
}