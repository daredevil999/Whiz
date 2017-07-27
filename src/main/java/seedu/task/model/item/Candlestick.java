package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class Candlestick {
	private Date date;
    private Price open;
    private Price close;
    private Price low;
    private Price high;
    private PriceChange change;
    //private BasicStickType type;

    public Candlestick(Date dateInput, Price open, Price close, Price low, Price high, PriceChange change) throws IllegalValueException {
    	this.date = dateInput;
    	this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.change = change;
    }
    
    public Date getDate () {
    	return date;
    }
    
	public Price getOpenPrice() {
		return open;
	}
	
	public Price getClosePrice() {
		return close;
	}
	
	public Price getLowPrice() {
		return low;
	}
	
	public Price getHighPrice() {
		return high;
	}
	
	public PriceChange getPriceChange() {
		return change;
	}
	
//	public BasicStickType getCandleStickType() {
//		return type;
//	}

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate().toString())
        		.append(" Open:  ")
        		.append(getOpenPrice())
        		.append(" Close: ")
                .append(getClosePrice())
                .append(" High: ")
                .append(getHighPrice())
                .append(" Low: ")
                .append(getLowPrice())
                .append(" Change: ")
                .append(getPriceChange());
        return builder.toString();
    }

}
