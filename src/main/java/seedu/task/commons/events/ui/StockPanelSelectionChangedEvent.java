package seedu.task.commons.events.ui;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * Represents a selection change in the Task List Panel
 */
public class StockPanelSelectionChangedEvent extends BaseEvent {


    private final ReadOnlyStock newSelection;

    public StockPanelSelectionChangedEvent(ReadOnlyStock newSelection){
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