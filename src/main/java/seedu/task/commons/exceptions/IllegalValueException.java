package seedu.task.commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
@SuppressWarnings("serial")
public class IllegalValueException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }
}