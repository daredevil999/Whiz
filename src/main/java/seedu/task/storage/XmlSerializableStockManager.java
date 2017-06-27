package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.ReadOnlyStockManager;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueEventList;
import seedu.task.model.item.UniqueStockList;

/**
 * An Immutable TaskBook that is serializable to XML format
 */
@XmlRootElement(name = "stockmanager")
public class XmlSerializableStockManager implements ReadOnlyStockManager {

    @XmlElement
    private List<XmlAdaptedStock> stocks;
    @XmlElement
    private List<XmlAdaptedEvent> events;

    {
        stocks = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableStockManager() {}

    /**
     * Conversion
     */
    public XmlSerializableStockManager(ReadOnlyStockManager src) {
        stocks.addAll(src.getStockList().stream().map(XmlAdaptedStock::new).collect(Collectors.toList()));
        events.addAll(src.getEventList().stream().map(XmlAdaptedEvent::new).collect(Collectors.toList()));
    }



    @Override
    public UniqueStockList getUniqueStockList() {
        UniqueStockList lists = new UniqueStockList();
        for (XmlAdaptedStock t : stocks) {
            try {
                lists.add(t.toModelType());
            } catch (IllegalValueException e) {

            }
        }
        return lists;
    }

    @Override
    public List<ReadOnlyStock> getStockList() {
        return stocks.stream().map(p -> {
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
