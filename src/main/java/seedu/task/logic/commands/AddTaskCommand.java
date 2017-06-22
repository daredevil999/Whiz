package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToTaskListRequestEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList;

//@@author A0127570H
/**
 * Adds a task to the task book.
 * @author kian ming
 */

public class AddTaskCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
	public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book";

	private static final Boolean DEFAULT_STATUS = false;
	private final Logger logger = LogsCenter.getLogger(AddTaskCommand.class);

	private final Stock toAdd;

	/**
	 * Convenience constructor using raw values.
	 * @throws IllegalValueException
	 *             if any of the raw values are invalid
	 */

	public AddTaskCommand(String name, String description, String deadline) throws IllegalValueException {
	    
	    if (description.isEmpty() && deadline.isEmpty()) {
	        this.toAdd = new Stock(new Name(name), null, null, DEFAULT_STATUS);
	    } else if (deadline.isEmpty()) {
	        this.toAdd = new Stock(new Name(name), new Description(description), null, DEFAULT_STATUS);
	    } else if (description.isEmpty()) {
            this.toAdd = new Stock(new Name(name), null, new Date(deadline), DEFAULT_STATUS);
        } else {
            this.toAdd = new Stock(new Name(name), new Description(description), new Date(deadline), DEFAULT_STATUS);
        }
	}
	
	public AddTaskCommand(ReadOnlyStock t) {
		this.toAdd = new Stock(t);
	}

	/*
	 * Execute for add task command
	 * Newly added task is to be selected for easy viewing
	 * Done by posting a JumpToTaskListRequestEvent
	 */
	@Override
	public CommandResult execute() {
		assert model != null;
		logger.info("-------[Executing AddTaskCommand] " + this.toString() );
		try {
			model.addTask(toAdd);
			
			UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredTaskList();
			EventsCenter.getInstance().post(new JumpToTaskListRequestEvent(toAdd, lastShownList.indexOf(toAdd)));
			return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
		} catch (UniqueStockList.DuplicateTaskException e) {
			return new CommandResult(MESSAGE_DUPLICATE_TASK);
		}
	}

	@Override
	public CommandResult undo() {
		DeleteTaskCommand reverseCommand = new DeleteTaskCommand(toAdd);
		reverseCommand.setData(model);
		
		return reverseCommand.execute();
	}
	
	@Override
	public String toString() {
		return COMMAND_WORD +" "+ this.toAdd.getAsText();
	}
	
	

}
