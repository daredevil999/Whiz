package seedu.task.model;


import java.util.List;

import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueEventList;
import seedu.task.model.item.UniqueTaskList;

/**
 * Unmodifiable view of a stock manager
 */
public interface ReadOnlyManager {

    UniqueTaskList getUniqueTaskList();
    UniqueEventList getUniqueEventList();
    /**
     * Returns an unmodifiable view of tasks list
     */
    List<ReadOnlyStock> getTaskList();

    /**
     * Returns an unmodifiable view of events list
     */
    List<ReadOnlyEvent> getEventList();
}
