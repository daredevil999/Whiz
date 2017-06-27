package seedu.task.model.item;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.base.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.commons.exceptions.DuplicateDataException;
import seedu.task.commons.util.CollectionUtil;

/**
 * A list of stocks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Stock#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueStockList implements Iterable<Stock> {

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    @SuppressWarnings("serial")
	public static class StockNotFoundException extends Exception {}

    private final ObservableList<Stock> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty StockList.
     */
    public UniqueStockList() {}

    /**
     * Returns true if the list contains an equivalent stock as the given argument.
     */
    public boolean contains(ReadOnlyStock toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }
    
    public int findStockIndexWithName (String inputName) {
    	Predicate<Stock> predicate = c-> c.getStockName().getNameValue().equals(inputName);
    	Stock  obj = internalList.stream().filter(predicate).findFirst().get();
    	return internalList.indexOf(obj);
    }

    //@@author A0127570H  
    /**
     * Adds a stock to the list.
     */
    public void add(Stock toAdd) {
    	assert toAdd != null;
        if (contains(toAdd)) {
            int targetIndex = findStockIndexWithName(toAdd.getStockName().fullName);
            Stock newStock = internalList.get(targetIndex);
            if (toAdd.getStockPurchaseInstanceList().isPresent()) {
            	assert toAdd.getStockPurchaseInstanceList().get().size() == 1;
            	StockPurchaseInstance newInstance = toAdd.getStockPurchaseInstanceList().get().get(0);
            	newStock.addStockPurchaseInstance(newInstance);
            	internalList.set(targetIndex, newStock);
            }
        } else {
        	internalList.add(toAdd);
        }
    }
    
    /**
     * Replaces a stock in the list with the edited stock.
     *
     * @throws DuplicateStockException if the task to replaced is a duplicate of an existing task in the list.
     */
    public void edit(Stock toEdit, ReadOnlyStock targetTask) {
        assert toEdit != null && targetTask != null;
//        if (contains(toEdit)) {
//            throw new DuplicateStockException();
//        }
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
     * @throws StockNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyStock toRemove) throws StockNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new StockNotFoundException();
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
