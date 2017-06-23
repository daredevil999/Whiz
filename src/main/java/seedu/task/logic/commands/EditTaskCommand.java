package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToTaskListRequestEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Flag;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList;
import seedu.task.model.item.UniqueStockList.DuplicateTaskException;

//@@author A0127570H
/**
 * Executes editing of tasks according to the input argument.
 * @author kian ming
 */

public class EditTaskCommand extends EditCommand  {

    private final Logger logger = LogsCenter.getLogger(EditTaskCommand.class);
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    private static final Boolean TASK_DEFAULT_STATUS = false;
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book";
    public static final String MESSAGE_INVALID_DEADLINE_REMOVAL = "This task does not have a deadline";
    
    private Name newName;
    private Description newDescription;
    private Date newDeadline;
    private boolean isDeadlineToBeRemoved;
    
    private Stock editTask;
    private ReadOnlyStock targetTask;
    
    /**
     * Convenience constructor using raw values.
     * Only fields to be edited will have values parsed in.
     * 
     * For deadline task to be edited to a floating task, a remove flag "rm" is parsed in the deadline argument
     *  
     * @throws IllegalValueException
     *             if any of the raw values are invalid
     */
    public EditTaskCommand(Integer index, String name, String description, String deadline) throws IllegalValueException {
        
        setTargetIndex(index);
        isDeadlineToBeRemoved = checkPresenceOfRemoveDeadlineFlag(deadline);
        if (!name.isEmpty()) {
            newName = new Name(name);
        } 
        if (!description.isEmpty()) {
            newDescription = new Description(description);
        }
        if (!deadline.isEmpty() && !isDeadlineToBeRemoved) {
            newDeadline = new Date(deadline);
        }
    }

    private boolean checkPresenceOfRemoveDeadlineFlag(String deadline) {
        return deadline.equals(Flag.removeFlag);
    } 

	/**
     * Gets the task to be edited based on the index.
     * Only fields to be edited will have values updated.
     * Newly edited task is to be selected for easy viewing
     * @throws DuplicateTaskException 
     */
    @Override
    public CommandResult execute() {
        logger.info("-------[Executing EditTaskCommand]");
        try {
            UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredTaskList();        
            targetTask = lastShownList.get(getTargetIndex());

            editTask = editTask(targetTask);
            model.editTask(editTask, targetTask);
            EventsCenter.getInstance().post(new JumpToTaskListRequestEvent(editTask, getTargetIndex()));
            
            logger.info("-------[Executed EditTaskCommand]" + this.toString());
            
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editTask));

        } catch (UniqueStockList.DuplicateTaskException e) {
            logger.info("-------[Failed execution of EditTaskCommand]" + " Duplicate task");
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IndexOutOfBoundsException ie) {
            indicateAttemptToExecuteIncorrectCommand();
            logger.info("-------[Failed execution of EditTaskCommand]" + " Index out of bound");
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (IllegalValueException e) {
            indicateAttemptToExecuteIncorrectCommand();
            logger.info("-------[Failed execution of EditTaskCommand]" + " Illegal value");
            return new CommandResult(e.getMessage());
        } 
    }

    /**
     * Edits the necessary fields.
     * Assumes task completion status is reinstated to not completed.
     * @return task that has the fields according to edit requirements.
     * @throws IllegalValueException requesting to remove deadline for a floating target task
     */    
    private Stock editTask(ReadOnlyStock targetTask) throws IllegalValueException {
        
        if (newName == null) {
            newName = targetTask.getStockName();
        }
//        if (newDescription == null) {
//            newDescription = targetTask.getDescription().orElse(null);
//        }
//        if (isDeadlineToBeRemoved && !targetTask.getPurchaseDate().isPresent()) {
//            throw new IllegalValueException(MESSAGE_INVALID_DEADLINE_REMOVAL);
//        }
//        if (newDeadline == null && targetTask.getPurchaseDate().isPresent() && !isDeadlineToBeRemoved) {
//            newDeadline = targetTask.getPurchaseDate().get();
//        }
        return new Stock (this.newName);        
    }

	@Override
	public CommandResult undo() {
        try {
            model.editTask((Stock)targetTask, editTask);
            
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editTask));
        } catch (UniqueStockList.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
	}
	
	@Override
	public String toString() {
		return COMMAND_WORD+ " from " + this.targetTask.getAsText()
		+ " to " + this.editTask.getAsText();
	}

}
