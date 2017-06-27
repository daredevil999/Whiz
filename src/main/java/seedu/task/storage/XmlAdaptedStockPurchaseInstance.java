package seedu.task.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.Price;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * JAXB-friendly version of the a stock purchase instance.
 */
public class XmlAdaptedStockPurchaseInstance {
	
	@XmlElement(required = true)
	private String purchaseDate;
	
	@XmlElement(required = true)
	private String purchasePrice;
	
	@XmlElement(required = true)
	private String purchaseLots;

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedStockPurchaseInstance() {}

    public XmlAdaptedStockPurchaseInstance(StockPurchaseInstance source) {
    	purchaseDate = source.getPurchaseDate().toString();
    	purchasePrice = source.getPurchasePrice().toString();
    	int temp = source.getPurchaseLots();
    	purchaseLots = Integer.toString(temp);
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     * 	- if a description or deadline is a string, make it null.
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public StockPurchaseInstance toModelType() throws IllegalValueException {

        final Date purchaseDate = new Date(this.purchaseDate);
        final Price purchasePrice = new Price(this.purchasePrice);
        final int purchaseLots = Integer.parseInt(this.purchaseLots);
        
        return new StockPurchaseInstance(purchaseDate, purchasePrice, purchaseLots);
    }

}
