package seedu.task.commons.events.ui;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * Represents a selection change in the Purchased Stock List Panel
 */
public class PStockPanelSelectionChangedEvent extends BaseEvent {


    private final ReadOnlyStock newSelection;

    public PStockPanelSelectionChangedEvent(ReadOnlyStock newSelection){
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ReadOnlyStock getNewSelection() {
        return newSelection;
    }
}
