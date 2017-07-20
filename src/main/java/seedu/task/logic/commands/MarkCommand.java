package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.commons.events.ui.JumpToPurchasedStockListRequestEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;

/**
 * @@author A0121608N
 * Marks a task as completed using it's last displayed index from the taskbook.
 * 
 */
public class MarkCommand extends UndoableCommand {

    private static final boolean UNCOMPLETE_STATUS = false;
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n"
            + "Marks the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s";
    
    private  int targetIndex;
    private ReadOnlyStock taskToMark;
    
    private final Logger logger = LogsCenter.getLogger(MarkCommand.class);
    
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

	@Override
    public CommandResult execute() {
        assert model != null;
        logger.info("-------[Executing MarkCommand] " + this.toString() );
        
        UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredStockList();

        if (outOfBounds(lastShownList.size(),targetIndex)) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_PURCHASED_STOCK_DISPLAYED_INDEX);
        }
        
        taskToMark = lastShownList.get(targetIndex - 1);
        model.markStock(taskToMark); // list starts at zero
        if (true) {   //Task will be selected if being marked from completed to uncompleted
            EventsCenter.getInstance().post(new JumpToPurchasedStockListRequestEvent(taskToMark, targetIndex - 1));
        }

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, targetIndex));

    }

    private boolean outOfBounds(int listSize, int lastShownListIndex){
        return listSize < lastShownListIndex || lastShownListIndex < 1;
    }
	
	@Override
	public CommandResult undo() {
		model.markStock(taskToMark);
		targetIndex = model.getFilteredStockList().indexOf(taskToMark);
		return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, targetIndex+1));
	}


	@Override
	public String toString() {
		return COMMAND_WORD +" "+ this.targetIndex;
	}

}
