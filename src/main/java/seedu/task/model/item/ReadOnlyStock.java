package seedu.task.model.item;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A read-only immutable interface for a task in the task book.
 * Implementations should guarantee: 
 *      Details are present and not null, with the exception of Deadline field. 
 *      Field values are validated.
 */
public interface ReadOnlyStock {

    Name getStockName();
    Optional<ArrayList<StockPurchaseInstance>> getStockPurchaseInstanceList();
    
    //Optional<Description> getDescription();
    //Boolean getTaskStatus();
    //Optional<Date> getPurchaseDate();
    

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyStock other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getStockName().equals(this.getStockName()) // state checks here onwards
//                && other.getPurchaseDate().equals(this.getPurchaseDate())
//                && other.getTaskStatus().equals(this.getTaskStatus())
//                && other.getDescription().equals(this.getDescription())
                );
    }

    /**
     * Formats the stock as text, showing all stock details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getStockName())
                .append(getStockPurchaseInstancesToString());
//                .append(getFormalDescriptionToString())
//                .append(getFormalDeadlineToString())
//                .append(getTaskStatusToString())
        return builder.toString();
    }
    
    /**
     * Formats the description as text.
     * If null, empty string is returned
     */
//    default String getDescriptionToString() {
//        return getDescription().isPresent()? getDescription().get().toString() : "";
//    }
    
    default String getStockPurchaseInstancesToString() {
    	final StringBuilder builder = new StringBuilder();
    	if (getStockPurchaseInstanceList().isPresent()) {
        	int index = 1;
            for (StockPurchaseInstance inst : getStockPurchaseInstanceList().get()) {
            	builder.append(System.getProperty("line.separator"));
            	builder.append(" Instance " + index + ": ")
            			.append(inst.getAsText());
            }
        }
    	return builder.toString();
	}
    
    /**
     * Formats the description as formal text.
     * If null, empty string is returned
     */
//    default String getFormalDescriptionToString() {
//        return getDescription().isPresent()? " Desc: " + getDescription().get().toString() : "";
//    }

}
