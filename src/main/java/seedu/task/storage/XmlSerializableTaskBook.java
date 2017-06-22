package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.ReadOnlyManager;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueEventList;
import seedu.task.model.item.UniqueStockList;

/**
 * An Immutable TaskBook that is serializable to XML format
 */
@XmlRootElement(name = "taskbook")
public class XmlSerializableTaskBook implements ReadOnlyManager {

    @XmlElement
    private List<XmlAdaptedTask> tasks;
    @XmlElement
    private List<XmlAdaptedEvent> events;

    {
        tasks = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableTaskBook() {}

    /**
     * Conversion
     */
    public XmlSerializableTaskBook(ReadOnlyManager src) {
        tasks.addAll(src.getTaskList().stream().map(XmlAdaptedTask::new).collect(Collectors.toList()));
        events.addAll(src.getEventList().stream().map(XmlAdaptedEvent::new).collect(Collectors.toList()));
    }



    @Override
    public UniqueStockList getUniqueTaskList() {
        UniqueStockList lists = new UniqueStockList();
        for (XmlAdaptedTask t : tasks) {
            try {
                lists.add(t.toModelType());
            } catch (IllegalValueException e) {

            }
        }
        return lists;
    }

    @Override
    public List<ReadOnlyStock> getTaskList() {
        return tasks.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UniqueEventList getUniqueEventList() {
        UniqueEventList lists = new UniqueEventList();
        for (XmlAdaptedEvent t : events) {
            try {
                lists.add(t.toModelType());
            } catch (IllegalValueException e) {

            }
        }
        return lists;
    }

    @Override
    public List<ReadOnlyEvent> getEventList() {
        return events.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
