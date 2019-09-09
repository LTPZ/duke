import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * The parser to parse the commands
 */
public class Parser {
    protected String currCommand;

    /**
     * Constructor
     *
     * @param command the current command to parse
     */
    public Parser(String command) {
        this.currCommand = command;
    }

    /**
     * Return the first word of the command if it is the designated command type
     *
     * @return the first word of the command
     * @throws EntryException if the command cannot be understand
     */
    public String parseWord() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String command = tok.nextToken();
        if (!command.equals("bye") && !command.equals("delete") && !command.equals("done") &&
                !command.equals("list") && !command.equals("todo") && !command.equals("deadline")
                && !command.equals("event") && !command.equals("find"))
            throw new EntryException();
        return command;
    }

    /**
     * Return the index num
     *
     * @return the parsed index num
     * @throws EntryException if there is no num in the command
     */
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

    /**
     * Return the command for todo type
     *
     * @return the todo content
     * @throws Exception
     */
    public Task parseToDo() throws Exception {
        checkLackInfo();
        String content = currCommand.substring(currCommand.indexOf(" ") + 1);
        Task currTask = new Task(content, "todo");
        return currTask;
    }

    /**
     * Return the command for deadline type
     *
     * @return the deadline content
     * @throws Exception
     */
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

    /**
     * Return the command for finding key type
     *
     * @return the key content
     * @throws EntryException if the key is not there
     */
    public String findKey() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        tok.nextToken();
        checkLackInfo();
        String key = tok.nextToken();
        return key;
    }

    /**
     * Check if lack information
     *
     * @throws EntryException if the command lack information
     */
    public void checkLackInfo() throws EntryException {
        StringTokenizer tok = new StringTokenizer(currCommand);
        String tmp = tok.nextToken();
        if (!tok.hasMoreElements()) {
            throw new EntryException();
        }
    }

    /**
     * Check if the deadline time is there
     *
     * @throws TimeException if the time is not the designated format
     */
    public void checkDeadlineTime() throws TimeException {
        //check if there is no time
        if (currCommand.indexOf(" /by") == -1) {
            throw new TimeException();
        }
    }

    /**
     * Check if the event time is there
     *
     * @throws TimeException if the time is not the designated format
     */
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

