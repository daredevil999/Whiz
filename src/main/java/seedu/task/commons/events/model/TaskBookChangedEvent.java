package seedu.task.commons.events.model;

import seedu.task.commons.events.BaseEvent;
import seedu.task.model.ReadOnlyStockManager;

/** Indicates the TaskBook in the model has changed*/
public class TaskBookChangedEvent extends BaseEvent {

    public final ReadOnlyStockManager data;

    public TaskBookChangedEvent(ReadOnlyStockManager data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "[number of tasks " + data.getTaskList().size()+ "] [number of events "
        		+ data.getEventList().size() + "]" ;
    }
}
