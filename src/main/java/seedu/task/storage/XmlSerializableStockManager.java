package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.ReadOnlyStockManager;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.UniqueStockList;

/**
 * An Immutable StockManager that is serializable to XML format
 */
@XmlRootElement(name = "stockmanager")
public class XmlSerializableStockManager implements ReadOnlyStockManager {

    @XmlElement
    private List<XmlAdaptedStock> pStocks;
    
    @XmlElement
    private List<XmlAdaptedStock> tStocks;

    {
        pStocks = new ArrayList<>();
        tStocks = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableStockManager() {}

    /**
     * Conversion
     */
    public XmlSerializableStockManager(ReadOnlyStockManager src) {
        pStocks.addAll(src.getPStockList().stream().map(XmlAdaptedStock::new).collect(Collectors.toList()));
        tStocks.addAll(src.getTStockList().stream().map(XmlAdaptedStock::new).collect(Collectors.toList()));
    }

    @Override
    public UniqueStockList getUniquePStockList() {
        UniqueStockList lists = new UniqueStockList();
        for (XmlAdaptedStock p : pStocks) {
            try {
                lists.add(p.toModelType());
            } catch (IllegalValueException e) {

            }
        }
        return lists;
    }

    @Override
    public List<ReadOnlyStock> getPStockList() {
        return pStocks.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

	@Override
	public UniqueStockList getUniqueTStockList() {
		UniqueStockList lists = new UniqueStockList();
        for (XmlAdaptedStock t : tStocks) {
            try {
                lists.add(t.toModelType());
            } catch (IllegalValueException e) {

            }
        }
        return lists;
	}

	@Override
	public List<ReadOnlyStock> getTStockList() {
		return tStocks.stream().map(t -> {
            try {
                return t.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
	}
}
