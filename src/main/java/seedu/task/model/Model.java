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

    /** Returns the StockManager */
    ReadOnlyStockManager getStockManager();

    /** Deletes the given stock. */
    void deleteStock(ReadOnlyStock target) throws UniqueStockList.StockNotFoundException;
    
    /** Clears the list of stocks. */
    void clearStocks();

    /** Adds the given purchased stock */
    void addPStock(Stock stock);
    
    /** Adds the given tracked stock */
    void addTStock(Stock stock);
    
    /** Edits the given stock */
    void editStock(Stock editStock, ReadOnlyStock targetTask);
    
    /** Marks the given stock */
    void markStock(ReadOnlyStock target);

    /** Returns the filtered purchased stock list as an {@code UnmodifiableObservableList<ReadOnlyStock>} */
    UnmodifiableObservableList<ReadOnlyStock> getFilteredPStockList();

    /** Updates the filter of the filtered purchased stock list to show all stocks */
    void updateFilteredPStockListToShowAll();
    
    /** Returns the filtered tracked stock list as an {@code UnmodifiableObservableList<ReadOnlyStock>} */
    UnmodifiableObservableList<ReadOnlyStock> getFilteredTStockList();

    /** Updates the filter of the filtered tracked stock list to show all stocks */
    void updateFilteredTStockListToShowAll();

    /** Updates the filter of the filtered stock list to filter by the given keywords*/
    void showFoundStockList(Set<String> keywords, boolean isPowerFind);
    
    /** Updates the filter of the filtered stock list to filter by the status*/
    void updateFilteredStockListToShowWithStatus(Status statusCompleted);


}
