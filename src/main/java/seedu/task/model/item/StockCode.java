package seedu.task.model.item;

import seedu.task.commons.exceptions.IllegalValueException;

public class StockCode {
	
	public static final String MESSAGE_STOCKCODE_CONSTRAINTS = "Stock codes should correspond to list of SGX codes";

    public final String code;

    public StockCode(String codeInput) throws IllegalValueException {
        assert codeInput != null;
        codeInput = codeInput.trim();
        if (!isValidStockCode(codeInput)) {
            throw new IllegalValueException(MESSAGE_STOCKCODE_CONSTRAINTS);
        }
        this.code = codeInput;
    }

    /**
     * Returns true if a given string is a valid stock code.
     */
    public static boolean isValidStockCode(String test) {
        getAllStockCodes();
    	return true;
    }
    
    private static void getAllStockCodes() {
		// TODO Auto-generated method stub		
	}

	public String getStockCode() {
    	return code;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.code.equals(((StockCode) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
