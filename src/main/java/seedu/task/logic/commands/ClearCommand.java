package seedu.task.logic.commands;

import seedu.task.model.ReadOnlyStockManager;
import seedu.task.model.StockManager;
import seedu.task.model.item.UniqueStockList;

/**
 * @@author A0121608N
 * Clears the taskbook's tasks and events according to the tags called
 */
public class ClearCommand extends UndoableCommand {


    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All %s %s has been cleared!";
    public static final String MESSAGE_RESTORED = "All data has been restored!";
    public static final String MESSAGE_COMPLETED = "completed";
    public static final String MESSAGE_COMPLETED_UNCOMPLETED = "completed and uncompleted";
    public static final String MESSAGE_TASKS = "tasks";
    public static final String MESSAGE_TASKS_EVENTS = "tasks and events";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" 
            + "Clears completed/uncompleted tasks and/or events from the task book.\n\n"
            + "Clearing completed tasks.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + " /t\n\n"
            + "Clearing completed and uncompleted tasks.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + " /t /a\n\n"
            + "Clearing past events.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + " /e\n\n"
            + "Clearing past and upcoming events.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + " /e /a\n\n"
            + "Clearing completed tasks and past events.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + "\n\n"
            + "Clearing all tasks and events.\n"
            + "Parameters: CLEAR_TYPE + CLEAR_ALL\n"
            + "Example: " + COMMAND_WORD
            + " /a \n\n";
    
    public enum Type{ task,event,all}
    
    private final Type clearType;
    private final boolean clearAll;
    
    private ReadOnlyStockManager currentTaskBook;

    public ClearCommand(Type clearType, boolean clearAll) {
        this.clearType = clearType;
        this.clearAll = clearAll;
    }


    @Override
    public CommandResult execute() {
        assert model != null;
        
        ReadOnlyStockManager taskbook = model.getStockManager();
        currentTaskBook = new StockManager(model.getStockManager());
        
        if(clearType == Type.all && !clearAll){ // clears completed tasks and events
            model.clearStocks();
            return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_COMPLETED, MESSAGE_TASKS_EVENTS));
        }else if (clearType == Type.task && !clearAll){ // clears completed tasks
            model.clearStocks();
            return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_COMPLETED, MESSAGE_TASKS));
        }else if (clearType == Type.task && clearAll){ // clears all completed and uncompleted tasks
            model.resetData(new StockManager(new UniqueStockList(), new UniqueStockList()));
            return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_COMPLETED_UNCOMPLETED, MESSAGE_TASKS));
        }else { // clears all completed and uncompleted tasks and events, only possible path left
            model.resetData(StockManager.getEmptyStockManager());
            return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_COMPLETED_UNCOMPLETED, MESSAGE_TASKS_EVENTS));
        }
    }


	@Override
	public CommandResult undo() {
		model.resetData(currentTaskBook);
		return new CommandResult(MESSAGE_RESTORED);
	}
}
