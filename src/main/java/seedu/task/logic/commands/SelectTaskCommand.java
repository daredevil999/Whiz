package seedu.task.logic.commands;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToPStockListRequestEvent;
import seedu.task.model.item.ReadOnlyStock;

//@author A0125534L

/**
 * Selects an Task identified using it's last displayed index from the task
 * book.
 * 
 */

public class SelectTaskCommand extends SelectCommand {

	public static final String MESSAGE_SELECT_TASK_SUCCESS = "Selected Task: %1$s";

	public SelectTaskCommand(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	@Override
	public CommandResult execute() {

		UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredStockList();
		
		//validation for input index greater than list size
		if (lastShownList.size() < targetIndex) { 
			indicateAttemptToExecuteIncorrectCommand();
			return new CommandResult(Messages.MESSAGE_INVALID_PURCHASED_STOCK_DISPLAYED_INDEX);
		}
		ReadOnlyStock targetTask = model.getFilteredStockList().get(targetIndex-1);
		EventsCenter.getInstance().post(new JumpToPStockListRequestEvent(targetTask, targetIndex - 1));
		return new CommandResult(String.format(MESSAGE_SELECT_TASK_SUCCESS, targetIndex));

	}
}
