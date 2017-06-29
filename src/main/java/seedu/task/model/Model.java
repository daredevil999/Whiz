package seedu.task.model;

import java.util.Set;

import seedu.task.commons.core.Status;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyStockManager newData);

    /** Returns the TaskBook */
    ReadOnlyStockManager getTaskBook();

    /** Deletes the given task. */
    void deleteTask(ReadOnlyStock target) throws UniqueStockList.StockNotFoundException;
    
    /** Clears the list of completed tasks. */
    void clearTasks();

    /** Adds the given stock */
    void addStock(Stock stock);
    
    /** Edits the given task */
    void editTask(Stock editTask, ReadOnlyStock targetTask);
    
    /** Marks the given task */
    void markTask(ReadOnlyStock target);

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>} */
    UnmodifiableObservableList<ReadOnlyStock> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredStockListToShowAll();

    /** Updates the filter of the filtered task list to filter by the given keywords*/
    void showFoundTaskList(Set<String> keywords, boolean isPowerFind);
    
    /** Updates the filter of the filtered task list to filter by the status*/
    void updateFilteredTaskListToShowWithStatus(Status statusCompleted);


}
