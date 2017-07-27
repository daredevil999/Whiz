package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class PriceChange {
	 public static final String MESSAGE_PRICE_CHANGE_CONSTRAINTS = "Stock price change sign should be + or -";

	    private final Price change;
	    private final String sign;

	    /**
	     * Validates given price change.
	     *
	     * @throws IllegalValueException if given price value is invalid.
	     */
	    public PriceChange(String input) throws IllegalValueException {
	        input = input.trim();
	        String signInput = String.valueOf(input.charAt(0));
	        String priceInput = input.substring(1);
	        
	    	if (!isValidPriceChangeSign(signInput)) {
	            throw new IllegalValueException(MESSAGE_PRICE_CHANGE_CONSTRAINTS);
	        }
	    	this.change = new Price(priceInput);
	    	this.sign = signInput;
	    }

		/**
	     * Returns true if a given string is a valid task name.
	     */
	    public static boolean isValidPriceChangeSign(String test) {
	    	return (test == "+" || test == "-");
	    }
	    
	    public Price getPriceChange() { 
	    	return this.change;
	    }
	    
	    public boolean getSign() {
	    	return sign == "+";
	    }

	    @Override
	    public String toString() {
	    	return this.sign + this.change.toString();
	    }

	    @Override
	    public boolean equals(Object other) {
	        return other == this // short circuit if same object
	                || (other instanceof PriceChange // instanceof handles nulls
	                && this.change == ((PriceChange) other).change
	                && this.sign == ((PriceChange) other).sign); // state check
	    }

}
