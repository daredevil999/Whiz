package seedu.task.logic.commands;

/**
 * Deletes an item identified using it's last displayed index from the stock manager.
 * 
 * */
public abstract class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" 
    		+ "Deletes a purchased or tracked stock from the Whiz storage completely.\n\n"
            + "Deletes a purchased stock at the specified INDEX in the most recent purchased stock listing.\n"
            + "Parameters: DELETE_TYPE + INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " /ps" + " 1";

    public int lastShownListIndex;


    @Override
    public abstract CommandResult execute();

}
