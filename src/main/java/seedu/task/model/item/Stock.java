package seedu.task.model.item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.CollectionUtil;

/**
 * Represents a Task in the task book.
 * Implementations should guarantee:    Details are present and not null, with the exception of Deadline field. 
 *                                      Field values are validated.
 */

public class Stock implements ReadOnlyStock {
    
    private Name name;
    //private ArrayList<StockPurchaseInstance> purchasedStocksList ;

    
    public Stock (Name name) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
    }

    /**
     * Copy constructor.
     * @throws IllegalValueException 
     */
    public Stock(ReadOnlyStock source) {
            this(source.getStockName());
//            		, source.getDescription().orElse(null), source.getPurchaseDate().orElse(null) , source.getTaskStatus());
    }

    @Override
    public Name getStockName() {
        return name;
    }

//    @Override
//    public Optional<Description> getDescription() {
//        return Optional.ofNullable(this.description);
//    }
//    
//   @Override
//    public Optional<Date> getPurchaseDate() { 
//       return Optional.ofNullable(this.deadline);
//    }
//
//    @Override
//    public Boolean getTaskStatus() {
//        return isTaskCompleted;
//    }
//    
//    public void toggleComplete() {
//    	isTaskCompleted = !isTaskCompleted;
//    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyStock // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyStock) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getAsText();
    }
    
//    //@@author A0144702N
//	/**
//	 * Sort deadline from earliest to latest
//	 * @param o
//	 * @return
//	 */
//	public static Comparator<Stock> getAscComparator() {
//		//first by deadline
//		Comparator<Stock> byDeadline = (t1, t2) -> {
//			if(!t1.getPurchaseDate().isPresent() && !t2.getPurchaseDate().isPresent())
//				return 0;
//			// if this is a floating task, it will be on the top
//			if(!t1.getPurchaseDate().isPresent())
//				return 1;
//			if(!t2.getPurchaseDate().isPresent()) 
//				return -1;
//			
//			//if both are not floating tasks 
//			return t1.getPurchaseDate().get().compareTo(t2.getPurchaseDate().get());
//		};
//		
//		//then by name
//		Comparator<Stock> byName = (t1, t2) -> t1.getStockName().compareTo(t2.getStockName());
//		
//		return byDeadline.thenComparing(byName);
//	}
//	
//	/**
//	 * Sort deadline from latest to earliest
//	 * @param o
//	 * @return
//	 */
//	public int sortDesc(Stock o) {
//		if(!this.getPurchaseDate().isPresent() && !o.getPurchaseDate().isPresent())
//			return 0;
//		// if this is a floating task, it will be on the top
//		if(!this.getPurchaseDate().isPresent())
//			return 1;
//		// if this is 
//		if(!o.getPurchaseDate().isPresent()) 
//			return -1;
//		return this.getPurchaseDate().get().compareTo(o.getPurchaseDate().get())*(-1);
//		
//	}


}
