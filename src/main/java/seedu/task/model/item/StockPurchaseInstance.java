package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class StockPurchaseInstance {

    public final Date purchaseDate;
    public final Price purchasePrice;
    public final int purchaseLots;

    public StockPurchaseInstance(Date dateInput, Price priceInput, int lotsInput) throws IllegalValueException {
    	this.purchaseDate = dateInput;
    	this.purchasePrice = priceInput;
        this.purchaseLots = lotsInput;
    }
    
    public Date getPurchaseDate () {
    	return purchaseDate;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Purchase Date: ")
        		.append(getPurchaseDate())
        		.append(" Purchase Price: ")
                .append(getPurchasePrice())
                .append(" Purchase Lots: ")
                .append(getPurchaseLots());
        
        return builder.toString();
    }

	private int getPurchaseLots() {
		return purchaseLots;
	}

	private Price getPurchasePrice() {
		return purchasePrice;
	}
}
