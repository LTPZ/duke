import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Parser {
    protected String currCommand;

    public Parser(String command) {
        this.currCommand = command;
    }

    public String parseWord() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command = tok.nextToken();
        if (!command.equals("bye") && !command.equals("delete") && !command.equals("done") &&
                !command.equals("list") && !command.equals("todo") && !command.equals("deadline")
                && !command.equals("event") && !command.equals("find"))
            throw new EntryException();
        return command;
    }

    public int parseIndex() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        tok.nextToken();
        //check if more information is needed
        checkLackInfo();
        //search for the delete number
        String numText = tok.nextToken();
        int num = Integer.parseInt(numText) - 1;
        return num;
    }

    public Task parseToDo() throws Exception {
        checkLackInfo();
        String content = currCommand.substring(currCommand.indexOf(" ") + 1);
        Task currTask = new Task(content, "todo");
        return currTask;
    }

    public Deadline parseDeadline() throws Exception {
        checkLackInfo();
        checkDeadlineTime();
        String content = currCommand.substring(currCommand.indexOf(" ") + 1, currCommand.indexOf(" /by"));
        String time = currCommand.substring(currCommand.indexOf(" /by") + 5);
        Date by = new SimpleDateFormat("dd/MM/yyyy").parse(time);
        Deadline deadline = new Deadline(content, false, "deadline", by, time);
        return deadline;
    }

    public Even parseEvent() throws Exception {
        checkLackInfo();
        checkEventTime();
        String content = currCommand.substring(currCommand.indexOf(" ") + 1, currCommand.indexOf(" /at"));
        String time = currCommand.substring(currCommand.indexOf(" /at") + 5);
        Date by = new SimpleDateFormat("dd/MM/yyyy").parse(time);
        Even event = new Even(content, false, "event", by, time);
        return event;
    }

    public String findKey() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        tok.nextToken();
        checkLackInfo();
        String key = tok.nextToken();
        return key;
    }

    public void checkLackInfo() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String tmp = tok.nextToken();
        if (!tok.hasMoreElements()) {
            throw new EntryException();
        }
    }

    public void checkDeadlineTime() throws TimeException {
        //check if there is no time
        if (currCommand.indexOf(" /by") == -1) {
            throw new TimeException();
        }
    }

    public void checkEventTime() throws TimeException {
        //check if there is no time
        if (currCommand.indexOf(" /at") == -1) {
            throw new TimeException();
        }
    }

    class TimeException extends Exception {
    }

    class EntryException extends Exception {
    }
}

