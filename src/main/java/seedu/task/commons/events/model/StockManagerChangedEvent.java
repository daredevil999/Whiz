package seedu.task.commons.events.model;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.ReadOnlyStockManager;

/** Indicates the Stock Manager in the model has changed*/
public class StockManagerChangedEvent extends BaseEvent {

    public final ReadOnlyStockManager data;

    public StockManagerChangedEvent(ReadOnlyStockManager data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "[number of stocks " + data.getStockList().size()+ "] [number of events "
        		+ data.getEventList().size() + "]" ;
    }
}
