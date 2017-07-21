package seedu.task.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList;

/**
 * Wraps all data at the stock manager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class StockManager implements ReadOnlyStockManager {

    private final UniqueStockList pStocks;
    private final UniqueStockList tStocks;

    {
        pStocks = new UniqueStockList();
        tStocks = new UniqueStockList();
    }

    public StockManager() {}

    /**
     * Stocks are copied into this stock manager
     */
    public StockManager(ReadOnlyStockManager toBeCopied) {
        this(toBeCopied.getUniquePStockList(), toBeCopied.getUniqueTStockList());
    }

    /**
     * Purchased and Tracked Stocks are copied into this stock manager
     */
    public StockManager(UniqueStockList pStocks, UniqueStockList tStocks) {
        resetData(pStocks.getInternalList(), tStocks.getInternalList());
    }

    public static ReadOnlyStockManager getEmptyStockManager() {
        return new StockManager();
    }

//// list overwrite operations

    public ObservableList<Stock> getPStocks() {
        return pStocks.getInternalList();
    }
    
    public ObservableList<Stock> getTStocks() {
        return tStocks.getInternalList();
    }

    public void setPStocks(List<Stock> pStocks) {
        this.pStocks.getInternalList().setAll(pStocks);
    }
    
    public void setTStocks(List<Stock> tStocks) {
        this.tStocks.getInternalList().setAll(tStocks);
    }
    
    //@@author A0121608N
    public void resetData(Collection<? extends ReadOnlyStock> newPStocks, Collection<? extends ReadOnlyStock> newTStocks) {
        setPStocks(newPStocks.stream().map(Stock::new).collect(Collectors.toList()));
        setTStocks(newTStocks.stream().map(Stock::new).collect(Collectors.toList()));
    }

    public void resetData(ReadOnlyStockManager newData) {
        resetData(newData.getPStockList(), newData.getTStockList());
    }
    
//// stock-level operations

    /**
     * Adds a stock to the stock manager.
     *
     */
    public void addPStock(Stock pStock) {
        pStocks.add(pStock);
    }

    //@@author A0121608N
    /**
     * Removes a stock in the stock manager.
     *
     * @throws UniqueStockList.StockNotFoundException if specified stock does not exist.
     */
    public boolean removeStock(ReadOnlyStock key) throws UniqueStockList.StockNotFoundException {
        if (pStocks.remove(key)) {
            return true;
        } else {
            throw new UniqueStockList.StockNotFoundException();
        }
    }

    /**
     * Marks a stock in the stock manager.
     */
    public void markStock(ReadOnlyStock key){
        pStocks.mark(key);
	}
    //@@author A0127570H
    
    /**
     * Edits a stock in the stock manager.
     *
     */
    public void editStock(Stock editStock, ReadOnlyStock targetStock) {
        pStocks.edit(editStock, targetStock);
    }
    //@@author 
    
//// util methods

    @Override
    public String toString() {
        return pStocks.getInternalList().size() + " purchased stocks";
    }

    @Override
    public List<ReadOnlyStock> getPStockList() {
        return Collections.unmodifiableList(pStocks.getInternalList());
    }

    @Override
    public UniqueStockList getUniquePStockList() {
        return this.pStocks;
    }

	@Override
	public UniqueStockList getUniqueTStockList() {
		return this.tStocks;
	}

	@Override
	public List<ReadOnlyStock> getTStockList() {
		return Collections.unmodifiableList(tStocks.getInternalList());
	}

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StockManager // instanceof handles nulls
                && this.pStocks.equals(((StockManager) other).pStocks));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(pStocks);
    }

}
