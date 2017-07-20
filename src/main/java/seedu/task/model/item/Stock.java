package seedu.task.model.item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.CollectionUtil;
import seedu.task.storage.TxtStockCodeStorage;

/**
 * Represents a Stock in the stock manager.
 * Implementations should guarantee:    Details are present and not null, with the exception of Deadline field. 
 *                                      Field values are validated.
 */

public class Stock implements ReadOnlyStock {
	
	private Name name;
    private StockCode code;
    private List<Candlestick> candlesticks;
    private List<StockPurchaseInstance> purchasedStocksList;
    
    /*
     * Constructors
     */
    
    public Stock (Name name, StockCode code) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.code = code;
        this.purchasedStocksList = null;
        this.candlesticks = null;
    }
    
    public Stock (Name name, StockCode code, Candlestick input) {
    	assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.code = code;
        this.candlesticks = new ArrayList<>();
        this.candlesticks.add(input);
        this.purchasedStocksList = null;
    }
    
    public Stock (Name name, StockCode code, StockPurchaseInstance input) {
    	assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.code = code;
        this.purchasedStocksList = new ArrayList<>();
        this.purchasedStocksList.add(input);
        this.candlesticks = null;
    } 
    
    public Stock (Name name, StockCode code, List<StockPurchaseInstance> purchaseInstanceListInput, List<Candlestick> candlestickListInput) {
    	assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.code = code;
        this.purchasedStocksList = purchaseInstanceListInput;
        this.candlesticks = candlestickListInput;
    }

    /**
     * Copy constructor.
     * @throws IllegalValueException 
     */
    public Stock(ReadOnlyStock source) {
            this(source.getStockName(), 
            		source.getStockCode(), 
            		source.getStockPurchaseInstanceList().orElse(null),
            		source.getCandlestickList().orElse(null));
//            		, source.getDescription().orElse(null), source.getPurchaseDate().orElse(null) , source.getTaskStatus());
    }
    
    /*
     * Public accessors
     */
    
    public void addStockPurchaseInstance (StockPurchaseInstance input) {
    	if (this.getStockPurchaseInstanceList().isPresent()) {
    		purchasedStocksList.add(input);
    	} else {
    		this.purchasedStocksList = new ArrayList<StockPurchaseInstance>();
    		purchasedStocksList.add(input);
    	}
    }
    
    public int getTotalStockPurchaseLots () {
    	int totalLots = 0;
    	for (StockPurchaseInstance inst : purchasedStocksList) {
    		totalLots += inst.purchaseLots;
    	}
    	return totalLots;
    }
    
    public Price getAverageStockPurchasePrice () {
    	int averagePrice = 0;
    	int totalLots = this.getTotalStockPurchaseLots();
    	for (StockPurchaseInstance inst : purchasedStocksList) {
    		averagePrice += (inst.purchasePrice.getPriceValueInInt() * inst.purchaseLots);
    	}
    	averagePrice /= totalLots;
    	return new Price (averagePrice);
    }
    
    @Override
    public Name getStockName() {
        return name;
    }

	@Override
	public Optional<List<StockPurchaseInstance>> getStockPurchaseInstanceList() {
		return Optional.ofNullable(this.purchasedStocksList);
	}

	@Override
	public Optional<List<Candlestick>> getCandlestickList() {
		return Optional.ofNullable(this.candlesticks);
	}

	@Override
	public StockCode getStockCode() {
		return code;
	}

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyStock // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyStock) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name,code);
    }

    @Override
    public String toString() {
        return getAsText();
    }
    
    //@@author A0144702N
	/**
	 * Sort deadline from earliest to latest
	 * @param o
	 * @return
	 */
	public static Comparator<Stock> getAscComparator() {
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
		
		//sort by name
		Comparator<Stock> byName = (t1, t2) -> t1.getStockName().compareTo(t2.getStockName());
		
		return byName;
	}

}
