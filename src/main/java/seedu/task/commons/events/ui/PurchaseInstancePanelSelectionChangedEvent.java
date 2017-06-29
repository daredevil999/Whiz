package seedu.task.commons.events.ui;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * Represents a selection change in the Purchase Instance List Panel
 */
public class PurchaseInstancePanelSelectionChangedEvent extends BaseEvent {


    private final StockPurchaseInstance newSelection;

    public PurchaseInstancePanelSelectionChangedEvent(StockPurchaseInstance newSelection){
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public StockPurchaseInstance getNewSelection() {
        return newSelection;
    }
}
