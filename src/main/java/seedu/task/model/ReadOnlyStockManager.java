package seedu.task.model;

import java.util.List;

import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueStockList;

/**
 * Unmodifiable view of a stock manager
 */
public interface ReadOnlyStockManager {

    UniqueStockList getUniquePStockList();
    /**
     * Returns an unmodifiable view of purchased stocks list
     */
    List<ReadOnlyStock> getPStockList();
    
    UniqueStockList getUniqueTStockList();
    /**
     * Returns an unmodifiable view of tracked stocks list
     */
    List<ReadOnlyStock> getTStockList();
}
