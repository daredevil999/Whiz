package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class Price {
	 public static final String MESSAGE_PRICE_CONSTRAINTS = "Stock prices should contain 1-3 decimal places";
	    public static final String PRICE_VALIDATION_REGEX = "[0-9]{1,13}(\\.[0-9]{1,3})?";

	    public final double value;

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
	        double valueInDouble = Double.parseDouble(value);
	        this.value = valueInDouble;
	    }

	    /**
	     * Returns true if a given string is a valid task name.
	     */
	    public static boolean isValidPrice(String test) {
	        return test.matches(PRICE_VALIDATION_REGEX);
	    }
	    
	    public double getPriceValue() {
	    	return value;
	    }

	    @Override
	    public String toString() {
	        return Double.toString(value);
	    }

	    @Override
	    public boolean equals(Object other) {
	        return other == this // short circuit if same object
	                || (other instanceof Price // instanceof handles nulls
	                && this.value == ((Price) other).value); // state check
	    }

	    @Override
	    public int hashCode() {
	        return Double.hashCode(value);
	    }

}
