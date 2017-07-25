package seedu.task.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String latestDateString = new String();

    @XmlElement
    private List<XmlAdaptedStockPurchaseInstance> purchased = new ArrayList<>();
    
    @XmlElement
    private HashMap<String, XmlAdaptedCandlestick> candlesticks = new HashMap<>();

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
        latestDateString = source.getLatestDateString();
        purchased = new ArrayList<>();
        if (source.getStockPurchaseInstanceList().isPresent()) {
        	for (StockPurchaseInstance inst : source.getStockPurchaseInstanceList().get()) {
                purchased.add(new XmlAdaptedStockPurchaseInstance(inst));
            }
        }
        candlesticks = new HashMap<>();
        if (source.getCandlestickMap().isPresent()) {
        	for (Map.Entry<String, Candlestick> entry : source.getCandlestickMap().get().entrySet()) {
                Candlestick stick = entry.getValue();
        		candlesticks.put(stick.getDate().toString(), new XmlAdaptedCandlestick(stick));
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
        final String dateString = this.latestDateString;
        final List<StockPurchaseInstance> purchasedList = new ArrayList<>();
        for (XmlAdaptedStockPurchaseInstance inst : purchased) {
        	purchasedList.add(inst.toModelType());
        }
        final Map<String, Candlestick> candlestickMap = new HashMap<>();
        for (Map.Entry<String, XmlAdaptedCandlestick> entry : candlesticks.entrySet()) {
        	Candlestick stick = entry.getValue().toModelType();
        	candlestickMap.put(stick.getDate().toString(), stick);
        } 
        return new Stock(name, code, dateString, purchasedList, candlestickMap);
    }
}
