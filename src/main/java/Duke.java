import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Duke {
    public static void main(String args[]) throws IOException, ParseException {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        //load data
        TaskList tasklist = new TaskList(0);
        File list = new File("D:/NUS/IDEs/Du/List.txt");
        BufferedReader reader = new BufferedReader(new FileReader(list));
        String oldData = null;
        while((oldData = reader.readLine()) != null) {
            //process old data
            tasklist.oldDataProcess(oldData);
        }
        reader.close();
        tasklist.scantoProcess();
    }
}