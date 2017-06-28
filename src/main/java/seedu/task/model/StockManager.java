package seedu.task.model;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.task.model.item.Event;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueEventList;
import seedu.task.model.item.UniqueStockList;
import seedu.task.storage.TxtStockCodeStorage;

/**
 * Wraps all data at the task-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class StockManager implements ReadOnlyStockManager {

    private final UniqueStockList myStocks;
    private final UniqueEventList events;

    {
        myStocks = new UniqueStockList();
        events = new UniqueEventList();
    }

    public StockManager() {}

    /**
     * Stocks are copied into this stock manager
     */
    public StockManager(ReadOnlyStockManager toBeCopied) {
        this(toBeCopied.getUniqueStockList(), toBeCopied.getUniqueEventList());
    }

    /**
     * Tasks and Events are copied into this stock manager
     */
    public StockManager(UniqueStockList tasks, UniqueEventList events) {
        resetData(tasks.getInternalList(), events.getInternalList());
    }

    public static ReadOnlyStockManager getEmptyTaskBook() {
        return new StockManager();
    }

//// list overwrite operations

    public ObservableList<Stock> getTasks() {
        return myStocks.getInternalList();
    }

    public void setTasks(List<Stock> tasks) {
        this.myStocks.getInternalList().setAll(tasks);
    }

    public ObservableList<Event> getEvents() {
        return events.getInternalList();
    }

    public void setEvents(List<Event> events) {
        this.events.getInternalList().setAll(events);
    }
    
    //@@author A0121608N
    public void resetData(Collection<? extends ReadOnlyStock> newTasks, Collection<? extends ReadOnlyEvent> newEvents) {
        setTasks(newTasks.stream().map(Stock::new).collect(Collectors.toList()));
        setEvents(newEvents.stream().map(Event::new).collect(Collectors.toList()));
    }

    public void resetData(ReadOnlyStockManager newData) {
        resetData(newData.getStockList(), newData.getEventList());
    }
    //@@author

//// event-level operations
    //@@author A0127570H
    /**
     * Adds an event to the task book.
     *
     * @throws UniqueEventList.DuplicateEventException if an equivalent event already exists.
     */
    public void addEvent(Event p) throws UniqueEventList.DuplicateEventException {
        events.add(p);
    }
    
    //@@author A0121608N
    /**
     * Removes an event in the task book.
     *
     * @throws UniqueStockList.EventNotFoundException if specified event does not exist.
     */
    public boolean removeEvent(ReadOnlyEvent key) throws UniqueEventList.EventNotFoundException {
        if (events.remove(key)) {
            return true;
        } else {
            throw new UniqueEventList.EventNotFoundException();
        }
    }
    //@@author A0127570H
    
    /**
     * Edits an event in the task book.
     *
     * @throws UniqueEventList.DuplicateEventException if an equivalent event already exists.
     */
    public void editEvent(Event editEvent, ReadOnlyEvent targetEvent) throws UniqueEventList.DuplicateEventException {
        events.edit(editEvent, targetEvent);
        
    }
    
//// stock-level operations

    /**
     * Adds a stock to the stock manager.
     *
     */
    public void addStock(Stock p) {
        myStocks.add(p);
    }

    //@@author A0121608N
    /**
     * Removes a task in the task book.
     *
     * @throws UniqueStockList.StockNotFoundException if specified task does not exist.
     */
    public boolean removeTask(ReadOnlyStock key) throws UniqueStockList.StockNotFoundException {
        if (myStocks.remove(key)) {
            return true;
        } else {
            throw new UniqueStockList.StockNotFoundException();
        }
    }

    /**
     * Marks a task in the task book.
     */
    public void markTask(ReadOnlyStock key){
        myStocks.mark(key);
	}
    //@@author A0127570H
    
    /**
     * Edits a task in the task book.
     *
     */
    public void editTask(Stock editTask, ReadOnlyStock targetTask) {
        myStocks.edit(editTask, targetTask);
    }
    //@@author 
    
//// util methods

    @Override
    public String toString() {
        return myStocks.getInternalList().size() + " tasks";
        
    }

    @Override
    public List<ReadOnlyStock> getStockList() {
        return Collections.unmodifiableList(myStocks.getInternalList());
    }

    @Override
    public UniqueStockList getUniqueStockList() {
        return this.myStocks;
    }
    
    @Override
    public List<ReadOnlyEvent> getEventList() {
        return Collections.unmodifiableList(events.getInternalList());
    }

    @Override
    public UniqueEventList getUniqueEventList() {
        return this.events;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StockManager // instanceof handles nulls
                && this.myStocks.equals(((StockManager) other).myStocks));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(myStocks);
    }
}
