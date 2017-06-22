package seedu.task.logic;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.task.logic.commands.CommandResult;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     */
    CommandResult execute(String commandText);

    /** Returns the filtered list of tasks */
    ObservableList<ReadOnlyStock> getFilteredTaskList();
    
    /** Returns the filtered list of events */
    ObservableList<ReadOnlyEvent> getFilteredEventList();
    
    //@@author A0144702N
    /** Convenient method returns all the events */
    List<ReadOnlyEvent> getAllEvents();
    
    /** Convenient method returns all the tasks*/
    List<ReadOnlyStock> getAllTasks();
    
}
