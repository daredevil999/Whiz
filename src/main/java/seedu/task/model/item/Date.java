package seedu.task.model.item;

import java.time.LocalDateTime;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.StringUtil;

public class Date implements Comparable<Date> {

    public static final String MESSAGE_DATE_CONSTRAINTS = 
    		"Date can be in the following format: \n"
    		+ "DAY HH:MM[:SS], eg: Monday 12:00[:30]\n"
    		+ "M D Y, eg: Oct 12 2016\n"
    		+ "M/D/Y, eg: 01/30/16\n"
    		+ "RELATIVE_DAY TIME, tomorrow 4pm\n";

    private LocalDateTime date; 

    /**
     * Validates given deadline.
     *
     * @throws IllegalValueException if given deadline string is invalid.
     */
    public Date(String deadlineArg) throws IllegalValueException{
        assert deadlineArg != null;
        deadlineArg = deadlineArg.trim();          
        try {
        	this.date = StringUtil.parseStringToTime(deadlineArg);
        } catch (IllegalValueException e) {
        	throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
    }
    
    public LocalDateTime getTime() {
    	return this.date;
    }


    @Override
    public String toString() {
        return this.date.format(StringUtil.DATE_FORMATTER);
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.toString() == null) ? 0 : this.toString().hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Date other = (Date) obj;
		if (date == null && (other.date != null)) {
			return false;
		} else if (!this.toString().equals(other.toString())) /*Standardized String to compare for equality */ {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Date o) {
		return this.date.compareTo(o.date);
	}

    

}
