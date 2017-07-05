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
        		.append(getPurchaseDate().getDate())
        		.append(" Purchase Price: ")
                .append(getPurchasePrice())
                .append(" Purchase Lots: ")
                .append(getPurchaseLots());
        return builder.toString();
    }
    
    public String getAsDisplayText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPurchaseLots())
        		.append(" lots purchased on ")
        		.append(getPurchaseDate().getDate())
        		.append(" at $")
                .append(getPurchasePrice());
        return builder.toString();
    }

	public int getPurchaseLots() {
		return purchaseLots;
	}

	public Price getPurchasePrice() {
		return purchasePrice;
	}
}
