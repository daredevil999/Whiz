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
    Optional<Description> getDescription();
    Optional<Date> getPurchaseDate();
    //Optional<ArrayList<StockPurchaseInstance>> getStockPurchaseInstanceList();
    Boolean getTaskStatus();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyStock other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getStockName().equals(this.getStockName()) // state checks here onwards
                && other.getPurchaseDate().equals(this.getPurchaseDate())
                && other.getTaskStatus().equals(this.getTaskStatus())
                && other.getDescription().equals(this.getDescription()));
    }

    /**
     * Formats the task as text, showing all task details and status.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getStockName())
                .append(getFormalDescriptionToString())
                .append(getFormalDeadlineToString())
                .append(getTaskStatusToString());
        
        return builder.toString();
    }
    
    /**
     * Formats the description as text.
     * If null, empty string is returned
     */
    default String getDescriptionToString() {
        return getDescription().isPresent()? getDescription().get().toString() : "";
    }
    
    /**
     * Formats the description as formal text.
     * If null, empty string is returned
     */
    default String getFormalDescriptionToString() {
        return getDescription().isPresent()? " Desc: " + getDescription().get().toString() : "";
    }
    
    /**
     * Formats the deadline as text.
     * If null, empty string is returned
     */
    default String getDeadlineToString() {
        return getPurchaseDate().isPresent()? getPurchaseDate().get().toString() : "";
    }
    
    /**
     * Formats the deadline as formal text.
     * If null, empty string is returned
     */
    default String getFormalDeadlineToString() {
        return getPurchaseDate().isPresent()? " By: " + getPurchaseDate().get().toString() : "";
    }
    
    /**
     * Formats the deadline as string.
     * If null, empty string is returned
     */
    default String getDeadlineValue() {
        return getPurchaseDate().isPresent()? getPurchaseDate().get().toString() : "";
    }
    
    /**
     * Formats the description as string.
     * If null, empty string is returned
     */
    default String getDescriptionValue() {
        return getDescription().isPresent()? getDescription().get().toString() : "";
    }
    
    /**
     * Formats the task status as text
     */
    default String getTaskStatusToString() {
        return getTaskStatus() ? " Status: Completed" : " Status: Not completed";
    }
    
    /**
     * Appends the name of a task with [DONE] if task is completed
     */
    default String getNameWithStatus() {
        return getTaskStatus() ? getStockName().toString() + " [DONE]" : getStockName().toString();
    }


}
