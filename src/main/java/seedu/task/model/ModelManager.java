package seedu.task.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.task.commons.core.ComponentManager;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Status;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.model.StockManagerChangedEvent;
import seedu.task.commons.util.StringUtil;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList;
import seedu.task.model.item.UniqueStockList.StockNotFoundException;


/**
 * Represents the in-memory model of the task book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Status ALL_STATUS = Status.ALL;
    
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StockManager stockManager;
    private final FilteredList<Stock> filteredPStocks;
    private final FilteredList<Stock> filteredTStocks;

    /**
     * Initializes a ModelManager with the given StockManager
     * StockManager and its variables should not be null
     */
    public ModelManager(StockManager src, UserPrefs userPrefs) {
        super();
        assert src != null;
        assert userPrefs != null;

        logger.fine("Initializing with stock manager: " + src + " and user prefs " + userPrefs);

        stockManager = new StockManager(src);
        filteredPStocks = new FilteredList<>(stockManager.getPStocks());
        filteredTStocks = new FilteredList<>(stockManager.getTStocks());
    }

    public ModelManager() {
        this(new StockManager(), new UserPrefs());
    }

    public ModelManager(ReadOnlyStockManager initialData, UserPrefs userPrefs) {
        stockManager = new StockManager(initialData);
        filteredPStocks = new FilteredList<>(stockManager.getPStocks());
        filteredTStocks = new FilteredList<>(stockManager.getTStocks());
    }

	@Override
    public void resetData(ReadOnlyStockManager newData) {
        stockManager.resetData(newData);
        updateFilteredStockListToShowWithStatus(ALL_STATUS);
        indicateStockManagerChanged();
    }

    @Override
    public ReadOnlyStockManager getStockManager() {
        return stockManager;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateStockManagerChanged() {
        raise(new StockManagerChangedEvent(stockManager));
    }

    //@@author A0121608N
    @Override
    public synchronized void deleteStock(ReadOnlyStock target) throws StockNotFoundException {
        stockManager.removeStock(target);
        updateFilteredStockListToShowWithStatus(ALL_STATUS);
        indicateStockManagerChanged();
    }
    
    @Override
    public synchronized void clearStocks() {
        
        updateFilteredStockListToShowWithStatus(ALL_STATUS);
        while(!filteredPStocks.isEmpty()){
            ReadOnlyStock task = filteredPStocks.get(0);
            try {
                stockManager.removeStock(task);
            } catch (StockNotFoundException snfe) {
                assert false : "The target stock cannot be missing";
            }
        }
        updateFilteredPStockListToShowAll();
        indicateStockManagerChanged();
    }

    @Override
    public synchronized void markStock(ReadOnlyStock target){
        stockManager.markStock(target);
        updateFilteredStockListToShowWithStatus(ALL_STATUS);
        indicateStockManagerChanged();
    }
    
    //@@author A0127570H

    @Override
    public synchronized void addPStock(Stock stock) {
        stockManager.addPStock(stock);
        updateFilteredPStockListToShowAll();
        indicateStockManagerChanged();
    }

	@Override
	public synchronized void addTStock(Stock stock) {
		stockManager.addTStock(stock);
        updateFilteredTStockListToShowAll();
        indicateStockManagerChanged();
		
	}
   
    @Override
    public synchronized void editStock(Stock editStock, ReadOnlyStock targetStock) {
        stockManager.editStock(editStock, targetStock);
        updateFilteredPStockListToShowAll();
        indicateStockManagerChanged();   
    }
    //@@author 
        
   
    //=========== Filtered List Accessors ===============================================================

    //@@author A0144702N
    @Override
    public UnmodifiableObservableList<ReadOnlyStock> getFilteredPStockList() {
    	SortedList<Stock> sortedPStocks = new SortedList<>(filteredPStocks);
    	return new UnmodifiableObservableList<>(sortedPStocks);
    }

    @Override
    public void updateFilteredPStockListToShowAll() {
        filteredPStocks.setPredicate(null);
    }

    @Override
    public void showFoundStockList(Set<String> keywords, boolean isPowerSearch){
        updateFilteredTaskList(new PredicateExpression(new NameQualifier(keywords, isPowerSearch)));
    }
    
	@Override
	public UnmodifiableObservableList<ReadOnlyStock> getFilteredTStockList() {
    	SortedList<Stock> sortedTStocks = new SortedList<>(filteredTStocks);
    	return new UnmodifiableObservableList<>(sortedTStocks);
	}

	@Override
	public void updateFilteredTStockListToShowAll() {
		filteredTStocks.setPredicate(null);
		
	}
    
    @Override
	public void updateFilteredStockListToShowWithStatus(Status status) {
    	if(status == Status.ALL) {
    		updateFilteredPStockListToShowAll();
    	} else {
    		updateFilteredTaskList(new PredicateExpression(new StatusQualifier(status)));
    	}
	}
    
    private void updateFilteredTaskList(Expression expression) {
        filteredPStocks.setPredicate(expression::satisfies);
    }

    //========== Inner classes/interfaces used for filtering ==================================================

    interface Expression {
        boolean satisfies(ReadOnlyStock task);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyStock task) {
        	qualifier.prepare(task);
            return qualifier.run();
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
		boolean run();
		void prepare(ReadOnlyStock task);
        String toString();
    }

    private class NameQualifier implements Qualifier {
    	private boolean isPowerSearch;
    	private Set<String> keyWords;
    	
        private String targetName;
        private String targetDesc; 
        
        
        NameQualifier(Set<String> keyWords, boolean isPowerSearch) {
            this.keyWords = keyWords;
            this.isPowerSearch = isPowerSearch;
        }

        @Override
        public String toString() {
            return String.join(", ", keyWords);
        }

		@Override
		/**
		 * Filter out those having names and description not matched with the keywords.
		 */
		public boolean run() {
    		List<String> sourceSet = new ArrayList<>();
    		
        	if(isPowerSearch) {
        		//break the name and desc to allow power search
        		sourceSet = new ArrayList<>(Arrays.asList(targetName.split("\\s")));
        		sourceSet.addAll(Arrays.asList(targetDesc.split("\\s")));
        		
        		//break the keyword to allow power search
        		List<String> tempSet = new ArrayList<>(keyWords);
        		keyWords = new HashSet<>();
        		tempSet.stream().forEach(keyword -> keyWords.addAll(Arrays.asList(keyword.split("\\s"))));
        		
        	} else {
        		sourceSet.add(targetName);
        		sourceSet.add(targetDesc);
        	}
        	
        	for(String source: sourceSet) {
    			boolean found = keyWords.stream()
                .filter(keyword -> StringUtil.isSimilar(source.trim(), keyword.trim()))
                .findAny()
                .isPresent();
    			
    			if (found) {
    				return true;
    			}
    		}
    		return false;
		}

		@Override
		public void prepare(ReadOnlyStock task) {
			targetName = task.getStockName().fullName;
		}
    }
    
    private class StatusQualifier implements Qualifier {
    	private Boolean status;
    	private Boolean targetStatus;
    	
    	StatusQualifier(Status status){
    		switch(status) {
    		case COMPLETED:
    			this.status = true;
    			break;
    		case INCOMPLETED:
    			this.status = false;
    			break;
    		default:
    			this.status = false;
    		}
    	}
    	
		@Override
		public boolean run() {
			return targetStatus.equals(status);
		}
		
		@Override 
		public String toString() {
			return (status ? "completed" : "not yet completed");  
		}

		@Override
		public void prepare(ReadOnlyStock task) {
			targetStatus = true;
		}    	
    }
}
