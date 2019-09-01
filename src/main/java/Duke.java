import java.io.IOException;
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
        //load data from the file
        Storage storage = new Storage();
        //load into new task list
        storage.readFile();
        TaskList taskList = new TaskList(storage.getList());
        //process the new command line
        Ui ui = new Ui();
        while(true) {
            ui.readCommand();
            Command command = new Command();
            Parser pa = new Parser(ui.getCurrCommand());
            try {
                if (pa.parseWord().equals("bye")) {
                    ui.printGoodbye();
                    break;
                } else if (pa.parseWord().equals("delete")) {
                    try {
                        int index = pa.parseIndex();
                        Task temp = taskList.deleteTask(index);
                        storage.changeFile(index, "delete");
                        ui.printRemove(temp.getType(), temp.toString(), taskList.getSize());
                    } catch (Parser.EntryException e) {
                        ui.printException("delete");
                    }
                } else if (pa.parseWord().equals("done")) {
                    try {
                        int index = pa.parseIndex();
                        Task temp = taskList.doneTask(index);
                        storage.changeFile(index, "done");
                        ui.printDone(temp.getType(), temp.toString(), taskList.getSize());
                    } catch (Parser.EntryException e) {
                        ui.printException("done");
                    }
                } else if (pa.parseWord().equals("list")) {
                    ui.printList(taskList.getList());
                } else if (pa.parseWord().equals("todo")) {
                    try {
                        Task temp = pa.parseToDo();
                        taskList.addToDo(temp);
                        storage.addFile(temp, "todo");
                        ui.printAdd(temp.toString(), taskList.getSize());
                    } catch (Parser.EntryException e) {
                        ui.printException("todo");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (pa.parseWord().equals("deadline")) {
                    try {
                        Deadline temp = pa.parseDeadline();
                        taskList.addDeadline(temp);
                        storage.addFile(temp,"deadline");
                        ui.printAdd(temp.toString(), taskList.getSize());
                    } catch (Parser.EntryException e) {
                        ui.printException("deadlineD");
                    } catch (Parser.TimeException e) {
                        ui.printException("deadlineT");
                    } catch (Exception e) {
                        ui.printException("deadlineTF");
                    }
                } else if (pa.parseWord().equals("event")) {
                    try {
                        Even temp = pa.parseEvent();
                        taskList.addEvent(temp);
                        storage.addFile(temp,"event");
                        ui.printAdd(temp.toString(), taskList.getSize());
                    } catch (Parser.EntryException e) {
                        ui.printException("eventE");
                    } catch (Parser.TimeException e) {
                        ui.printException("eventT");
                    } catch (Exception e) {
                        ui.printException("eventTF");
                    }
                } else {
                    try {
                        String key = pa.findKey();
                        taskList.findKey(key);
                    } catch (Parser.EntryException e) {
                        ui.printException("key");
                    }
                }
            } catch (Parser.EntryException e) {
                ui.printException("random");
            }
        }
    }
}