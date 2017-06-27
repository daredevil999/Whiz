package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * JAXB-friendly version of the Stock.
 */
public class XmlAdaptedStock {
	
	@XmlElement(required = true)
	private String name;

    @XmlElement
    private List<XmlAdaptedStockPurchaseInstance> purchased = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedStock() {}

    //@@author A0127570H
    /**
     * Converts a given task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedStock(ReadOnlyStock source) {
        name = source.getStockName().fullName;
        purchased = new ArrayList<>();
        if (source.getStockPurchaseInstanceList().isPresent()) {
        	for (StockPurchaseInstance inst : source.getStockPurchaseInstanceList().get()) {
                purchased.add(new XmlAdaptedStockPurchaseInstance(inst));
            }
        }
    }

    /**
     * Converts this jaxb-friendly adapted stock object into the model's Stock object.
     * 	- if a description or deadline is a string, make it null.
     * @throws IllegalValueException if there were any data constraints violated in the adapted stock
     */
    public Stock toModelType() throws IllegalValueException {

        final Name name = new Name(this.name);
        final List<StockPurchaseInstance> purchasedList = new ArrayList<>();
        for (XmlAdaptedStockPurchaseInstance inst : purchased) {
        	purchasedList.add(inst.toModelType());
        }       
        return new Stock(name, purchasedList);
    }
}
