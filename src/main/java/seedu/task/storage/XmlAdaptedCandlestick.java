package seedu.task.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Candlestick;
import seedu.task.model.item.Date;
import seedu.task.model.item.Price;
import seedu.task.model.item.PriceChange;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * JAXB-friendly version of a candlestick
 */
public class XmlAdaptedCandlestick {
	
	@XmlElement(required = true)
	private String open;
	
	@XmlElement(required = true)
	private String close;
	
	@XmlElement(required = true)
	private String low;
	
	@XmlElement(required = true)
	private String high;
	
	@XmlElement(required = true)
	private String date;
	
	@XmlElement(required = true)
	private String change;

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedCandlestick() {}

    public XmlAdaptedCandlestick(Candlestick source) {
    	open = source.getOpenPrice().toString();
    	close = source.getClosePrice().toString();
    	low = source.getLowPrice().toString();
    	high = source.getHighPrice().toString();
    	date = source.getDate().toString();
    	change = source.getPriceChange().toString();
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     * 	- if a description or deadline is a string, make it null.
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Candlestick toModelType() throws IllegalValueException {

        final Date date = new Date(this.date);
        final Price open = new Price(this.open);
        final Price close = new Price(this.close);
        final Price low = new Price(this.low);
        final Price high = new Price(this.high);
        final PriceChange change = new PriceChange(this.change);
        
        return new Candlestick(date,open,close,low,high,change);
    }

}
