import java.io.*;
import java.text.ParseException;

public class Duke {
    public static void main(String args[]) throws IOException, ParseException {
        //say hello
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
<<<<<<< HEAD
        //load data from the file
        TaskList taskList = new TaskList(0);
=======
        //load data
        TaskList tasklist = new TaskList(0);
>>>>>>> branch-Level-9
        File list = new File("D:/NUS/IDEs/Du/List.txt");
        BufferedReader reader = new BufferedReader(new FileReader(list));
        String oldData = null;
        while((oldData = reader.readLine()) != null) {
            //process old data
<<<<<<< HEAD
            taskList.oldDataProcess(oldData);
        }
        reader.close();
        //process the new command line
        taskList.scanProcess();
=======
            tasklist.oldDataProcess(oldData);
        }
        reader.close();
        tasklist.scantoProcess();
>>>>>>> branch-Level-9
    }
}