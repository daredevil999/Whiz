package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Candlestick;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockCode;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * JAXB-friendly version of the Stock.
 */
public class XmlAdaptedStock {
	
	@XmlElement(required = true)
	private String name;
	
	@XmlElement(required = true)
	private String code;

    @XmlElement
    private List<XmlAdaptedStockPurchaseInstance> purchased = new ArrayList<>();
    
    @XmlElement
    private List<XmlAdaptedCandlestick> candlesticks = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedStock() {}

    //@@author A0127570H
    /**
     * Converts a given stock into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedStock
     */
    public XmlAdaptedStock(ReadOnlyStock source) {
        name = source.getStockName().fullName;
        code = source.getStockCode().code;
        purchased = new ArrayList<>();
        if (source.getStockPurchaseInstanceList().isPresent()) {
        	for (StockPurchaseInstance inst : source.getStockPurchaseInstanceList().get()) {
                purchased.add(new XmlAdaptedStockPurchaseInstance(inst));
            }
        }
        candlesticks = new ArrayList<>();
        if (source.getCandlestickList().isPresent()) {
        	for (Candlestick stick : source.getCandlestickList().get()) {
                candlesticks.add(new XmlAdaptedCandlestick(stick));
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
        final StockCode code = new StockCode(this.code);
        final List<StockPurchaseInstance> purchasedList = new ArrayList<>();
        for (XmlAdaptedStockPurchaseInstance inst : purchased) {
        	purchasedList.add(inst.toModelType());
        }
        final List<Candlestick> candlestickList = new ArrayList<>();
        for (XmlAdaptedCandlestick stick : candlesticks) {
        	candlestickList.add(stick.toModelType());
        } 
        return new Stock(name, code, purchasedList, candlestickList);
    }
}
