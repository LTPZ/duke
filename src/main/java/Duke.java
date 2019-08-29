import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String args[]) throws IOException {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        //load data
        Database database = new Database(0);
        File list = new File("D:/NUS/IDEs/Du/List.txt");
        BufferedReader reader = new BufferedReader(new FileReader(list));
        String oldData = null;
        while((oldData = reader.readLine()) != null) {
            //process old data
            database.oldDataProcess(oldData);
        }
        reader.close();
        database.scantoProcess();
    }
}