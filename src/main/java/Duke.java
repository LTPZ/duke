import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

public class Duke {
    public static void main(String args[]) {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        //load data
        List<Tasks> lists = new ArrayList<Tasks>();
        int size = 0;
        //File list = new File("D:/NUS/IDEs/Du");
        //InputStream in = null;
        //FileReader reader = new FileReader(file)
        Command userEnter = new Command();
        userEnter.scantoProcess(lists, size);
    }
}