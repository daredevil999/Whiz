package seedu.task.logic.commands;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToEventListRequestEvent;
import seedu.task.model.item.ReadOnlyEvent;

//@@author A0125534L

/**
 * Selects an Event identified using it's last displayed index from the task
 * book.
 *
 */

public class SelectEventCommand extends SelectCommand {

	public static final String MESSAGE_SELECT_EVENT_SUCCESS = "Selected Event: %1$s";

	public SelectEventCommand(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	@Override
	public CommandResult execute() {

		UnmodifiableObservableList<ReadOnlyEvent> lastShownEventList = model.getFilteredEventList();

		//validation for input index greater than list size
		if (lastShownEventList.size() < targetIndex) { 
			indicateAttemptToExecuteIncorrectCommand();
			return new CommandResult(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
		}
		
		ReadOnlyEvent targetEvent = model.getFilteredEventList().get(targetIndex-1);
		
		EventsCenter.getInstance().post(new JumpToEventListRequestEvent(targetEvent, targetIndex - 1));
		return new CommandResult(String.format(MESSAGE_SELECT_EVENT_SUCCESS, targetIndex));

	}

}
