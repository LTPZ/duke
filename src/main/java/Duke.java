import java.util.Scanner;
import java.util.StringTokenizer;

class Lines {
    private String currLine;
    private String[] theList = new String[100];
    private int num = 0;
    private boolean[] doList = new boolean[100];
    private boolean Checkdone() {
        if (currLine.regionMatches(0,"done",0,4)) return true;
        else return false;
    }
    private void Donelist() {
        if (num != 0) {
            StringTokenizer tok = new StringTokenizer(currLine);
            String tmp = tok.nextToken();
            tmp = tok.nextToken();
            int i = Integer.parseInt(tmp);
            doList[i - 1] = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t[√] " + theList[i - 1]);
        }
    }
    private void Numincrease() {
        num++;
    }
    void Add() {
        theList[num] = currLine;
        doList[num] = false;
        Numincrease();
    }
    void Enter() {
        Scanner in = new Scanner(System.in);
        currLine = in.nextLine();
        if (!currLine.equals("list") && !Checkdone()) Add();
    }
    private void Showlist() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < num; ++i) {
            if (doList[i] == true) System.out.println("\t" + (i+1) + ".[√] " + theList[i]);
            else System.out.println("\t" + (i+1) + ".[×] " + theList[i]);
        }
    }
    boolean process() {
        if (currLine.equals("bye")) return true;
        else if (currLine.equals("list")) Showlist();
        else if (Checkdone()) Donelist();
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