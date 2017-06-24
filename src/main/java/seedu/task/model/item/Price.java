package seedu.task.model.item;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	    
	    public Price(double valInput) {
	    	this.value = roundToDP(valInput,3);
	    }

	    private double roundToDP(double valInput, int places) {
	    	assert(places > 0);

	        BigDecimal bd = new BigDecimal(value);
	        bd = bd.setScale(places, RoundingMode.HALF_UP);
	        return bd.doubleValue();
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
	        return Double.toString(roundToDP(this.value,3));
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
