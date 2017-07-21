package seedu.task.commons.events.ui;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.item.ReadOnlyStock;

//@@author A0144702N
/**
 * Indicates a request to jump to the list of items
 */
public class JumpToPStockListRequestEvent extends BaseEvent {

    public final int targetIndex;
    public final ReadOnlyStock targetStock;

    public JumpToPStockListRequestEvent(ReadOnlyStock stock, int targetIndex) {
        this.targetIndex = targetIndex;
        this.targetStock = stock;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
