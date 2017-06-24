package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class Price {
	 public static final String MESSAGE_PRICE_CONSTRAINTS = "Stock prices should contain 1-3 decimal places";
	    public static final String PRICE_VALIDATION_REGEX = "[0-9]{1,13}(\\.[0-9]{1,3})?";

	    private final int decimalValue;
	    private final int wholeValue;

	    /**
	     * Validates given price.
	     *
	     * @throws IllegalValueException if given price value is invalid.
	     */
	    public Price(String value) throws IllegalValueException {
	        value = value.trim();
	        if (!isValidPrice(value)) {
	            throw new IllegalValueException(MESSAGE_PRICE_CONSTRAINTS);
	        }
	        String[] splitStr = value.split("\\.");
	        if (splitStr.length == 1) {
	        	this.decimalValue = 0;
	        	this.wholeValue = Integer.parseInt(splitStr[0]);
	        } else {
	        	this.wholeValue = Integer.parseInt(splitStr[0]);
	        	this.decimalValue = Integer.parseInt(splitStr[1]);
	        }
	    }
	    
	    public Price(int value) {
	    	int temp = value / 1000;
	    	this.decimalValue = value - temp * 1000;
	    	this.wholeValue = temp;
	    }

		/**
	     * Returns true if a given string is a valid task name.
	     */
	    public static boolean isValidPrice(String test) {
	        return test.matches(PRICE_VALIDATION_REGEX);
	    }
	    
	    public double getPriceValueInDouble() { 
	    	double output = wholeValue + ((double)decimalValue / 1000);
	    	return output;
	    }
	    
	    public int getPriceValueInInt() { 
	    	int output = wholeValue * 1000 + decimalValue;
	    	return output;
	    }

	    @Override
	    public String toString() {
	    	double output = this.getPriceValueInDouble();
	    	return Double.toString(output);
	    }

	    @Override
	    public boolean equals(Object other) {
	        return other == this // short circuit if same object
	                || (other instanceof Price // instanceof handles nulls
	                && this.wholeValue == ((Price) other).wholeValue
	                && this.decimalValue == ((Price) other).decimalValue); // state check
	    }

}
