package seedu.task.logic.commands;

public abstract class TrackCommand extends UndoableCommand {
    
    public static final String COMMAND_WORD = "track";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" 
    		+ "Tracks a stock in the stock manager.\n"
            + "Parameters: " + COMMAND_WORD + "[STOCK_NAME || STOCKCODE]\n"
            + "Example: " + COMMAND_WORD
            + " DBS GROUP HOLDINGS LTD\n"
            + "Example with the use of Stock Code of DBS: \n" + COMMAND_WORD
            + " D05\n\n";
    
    /**
     * Executes the command and returns the result message.
     * @return feedback message of the operation result for display
     */
    public abstract CommandResult execute();
}
