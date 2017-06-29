package seedu.task.commons.exceptions;


//@@author A0127570H
/**
 * Signals that some given data is missing.
 */

@SuppressWarnings("serial")
public class MissingValueException extends Exception{

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public MissingValueException(String message) {
         super(message);
     }
}
