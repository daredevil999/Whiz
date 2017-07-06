package seedu.task.model.item;

import java.util.List;
import java.util.Optional;

/**
 * A read-only immutable interface for a task in the task book.
 * Implementations should guarantee: 
 *      Details are present and not null, with the exception of Deadline field. 
 *      Field values are validated.
 */
public interface ReadOnlyStock {

    Name getStockName();
    StockCode getStockCode();
    Optional<List<StockPurchaseInstance>> getStockPurchaseInstanceList();
    Optional<List<Candlestick>> getCandlestickList();
    
    //Optional<Description> getDescription();
    //Boolean getTaskStatus();
    //Optional<Date> getPurchaseDate();  

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyStock other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && (other.getStockName().equals(this.getStockName()) // state checks here onwards
                || other.getStockCode().equals(this.getStockCode()))
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
                .append(" | ")
                .append(getStockCode())
                .append(getStockPurchaseInstancesToString());
//                .append(getFormalDescriptionToString())
//                .append(getFormalDeadlineToString())
//                .append(getTaskStatusToString())
        return builder.toString();
    }
    
    /**
     * Formats the stock as text, showing all stock details.
     */
    default String getPurchasedStockAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getStockName())
                .append(" | ")
                .append(getStockCode())
                .append(getNewStockPurchaseInstanceToString());
//                .append(getFormalDescriptionToString())
//                .append(getFormalDeadlineToString())
//                .append(getTaskStatusToString())
        return builder.toString();
    }
    
    default String getNewStockPurchaseInstanceToString() {
    	final StringBuilder builder = new StringBuilder();
    	if (getStockPurchaseInstanceList().isPresent()) {
            for (StockPurchaseInstance inst : getStockPurchaseInstanceList().get()) {
            	builder.append(System.getProperty("line.separator"));
            	builder.append("New Instance " + ": ")
            			.append(inst.getAsText());
            }
        }
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
            	builder.append("Instance " + index + ": ")
            			.append(inst.getAsText());
            	index++;
            }
            return builder.toString();
        } else {
        	return "";
        }
	}
    
    default String getStockPurchaseInstancesAsDisplayText() {
    	final StringBuilder builder = new StringBuilder();
    	if (getStockPurchaseInstanceList().isPresent()) {
        	int index = 1;
            for (StockPurchaseInstance inst : getStockPurchaseInstanceList().get()) {
            	builder.append(System.getProperty("line.separator"));
            	builder.append(index + ": ")
            			.append(inst.getAsDisplayText());
            	index++;
            }
            return builder.toString();
        } else {
        	return "";
        }
	}
    
	default String getAveragePriceToString() {
	  if (getStockPurchaseInstanceList().isPresent()) {
		  return " Average Price: " + this.getAverageStockPurchasePrice().toString(); 
	  }
	  else {
		  return "";
	  }
	}
	
	default String getPurchasedLotsToString() {
		  if (getStockPurchaseInstanceList().isPresent()) {
			  return " Total Lots: " + this.getTotalStockPurchaseLots(); 
		  }
		  else {
			  return "";
		  }
	}
	
    default int getTotalStockPurchaseLots () {
    	int totalLots = 0;
    	if (getStockPurchaseInstanceList().isPresent()) {
    		List<StockPurchaseInstance> purchasedStocksList = getStockPurchaseInstanceList().get();
    		for (StockPurchaseInstance inst : purchasedStocksList) {
        		totalLots += inst.purchaseLots;
        	}
        	return totalLots;
    	}
    	else {
    		return 0;
    	}
    }
    
    default Price getAverageStockPurchasePrice () {
    	if (getStockPurchaseInstanceList().isPresent()) {
    		List<StockPurchaseInstance> purchasedStocksList = getStockPurchaseInstanceList().get();
    		int averagePrice = 0;
        	int totalLots = this.getTotalStockPurchaseLots();
        	for (StockPurchaseInstance inst : purchasedStocksList) {
        		averagePrice += (inst.purchasePrice.getPriceValueInInt() * inst.purchaseLots);
        	}
        	averagePrice /= totalLots;
        	return new Price (averagePrice);
    	}
    	else {
    		return null;
    	} 	
    }
    
    /**
     * Formats the description as formal text.
     * If null, empty string is returned
     */
//    default String getFormalDescriptionToString() {
//        return getDescription().isPresent()? " Desc: " + getDescription().get().toString() : "";
//    }

}
