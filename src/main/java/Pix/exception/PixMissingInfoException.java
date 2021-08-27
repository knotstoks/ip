package Pix.exception;

/**
 * Triggers when there is missing information in the Pix.command.
 */
public class PixMissingInfoException extends PixException {
    private String command;

    /**
     * Constructor of the PixMissingInfoException.
     */
    public PixMissingInfoException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        switch (command) {
        case "todo":
            return "The information of the todo Pix.task is incomplete!";
        case "deadline":
            return "The information of the deadline is incomplete!";
        case "event":
            return "The information of the event is incomplete!";
        case "done":
            return "Which Pix.task did you do?";
        case "list":
            return "You need to input a Pix.task number!";
        case "delete":
            return "The Pix.task cannot be found. Which Pix.task do you want deleted?";
        case "find":
            return "Key in 1 word to search for! O-N-E!";
        default:
            return "There is insufficient information!";
        }
    }
}