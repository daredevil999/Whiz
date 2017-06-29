package seedu.task.model;

import java.util.List;

import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueStockList;

/**
 * Unmodifiable view of a stock manager
 */
public interface ReadOnlyStockManager {

    UniqueStockList getUniqueStockList();
    /**
     * Returns an unmodifiable view of stocks list
     */
    List<ReadOnlyStock> getStockList();
}
