package seedu.task.model.item;

import java.util.Collection;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.commons.exceptions.DuplicateDataException;
import seedu.task.commons.util.CollectionUtil;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Stock#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueStockList implements Iterable<Stock> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    @SuppressWarnings("serial")
	public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    @SuppressWarnings("serial")
	public static class TaskNotFoundException extends Exception {}

    private final ObservableList<Stock> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty TaskList.
     */
    public UniqueStockList() {}

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyStock toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    //@@author A0127570H  
    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Stock toAdd) throws DuplicateTaskException {
    	assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }
    
    /**
     * Replaces a task in the list with the edited task.
     *
     * @throws DuplicateTaskException if the task to replaced is a duplicate of an existing task in the list.
     */
    public void edit(Stock toEdit, ReadOnlyStock targetTask) throws DuplicateTaskException {
        assert toEdit != null && targetTask != null;
        if (contains(toEdit)) {
            throw new DuplicateTaskException();
        }
        int index = internalList.indexOf(targetTask);
        internalList.set(index, toEdit);
    }

    //@@author
    
    /**
     * Marks a task in the list
     */
    public void mark(ReadOnlyStock toMark){
        assert toMark != null;
        
        int index = internalList.indexOf(toMark);
        Stock targetTask = internalList.get(index);
        internalList.set(index, targetTask);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyStock toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    public ObservableList<Stock> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Stock> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStockList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueStockList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
