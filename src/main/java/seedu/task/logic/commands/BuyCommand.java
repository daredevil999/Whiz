package seedu.task.logic.commands;

public abstract class BuyCommand extends UndoableCommand {
    
    public static final String COMMAND_WORD = "buy";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" 
    		+ "Adds a purchased stock to the stock manager.\n"
            + "The order of the parameters is flexible\n\n"
    		+ "Adding a newly purchased stock.\n"
            + "Parameters: [STOCK_NAME || STOCKCODE] /d DATEOFPURCHASE /l NUMOFSHARES /p PURCHASE PRICE \n"
    		+ "DATEOFPURCHASE can be in words or MM-DD-YY\n"
            + "Example: " + COMMAND_WORD
            + " DBS GROUP HOLDINGS LTD /l 1000 /p 23.05 /d today\n\n"
            + "Example with the use of Stock Code of DBS: \n" + COMMAND_WORD
            + " D05 /l 1000 /p 23.05 /d today\n\n";
    
    /**
     * Executes the command and returns the result message.
     * @return feedback message of the operation result for display
     */
    public abstract CommandResult execute();
}
