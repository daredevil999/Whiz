package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.UniqueStockList.StockNotFoundException;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;

/**
 * Deletes a Stock identified using it's last displayed index from the stockmanager.
 * 
 */
public class DeletePurchasedStockCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_PURCHASED_STOCK_SUCCESS = "Deleted Purchased Stock: %1$s";

    private ReadOnlyStock stockToDelete;
    
    private final Logger logger = LogsCenter.getLogger(DeletePurchasedStockCommand.class);
    
    public DeletePurchasedStockCommand(int targetIndex) {
        this.lastShownListIndex = targetIndex;
    }


	public DeletePurchasedStockCommand(Stock stockToDelete) {
        this.stockToDelete = stockToDelete;
    }


    @Override
    public CommandResult execute() {
        assert model != null;
        
        if(stockToDelete == null){
            UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredPStockList();
        
            if (outOfBounds(lastShownList.size())) {
                indicateAttemptToExecuteIncorrectCommand();
                return new CommandResult(Messages.MESSAGE_INVALID_PURCHASED_STOCK_DISPLAYED_INDEX);
            }
    
            stockToDelete = lastShownList.get(lastShownListIndex - 1);
        }
        
        logger.info("-------[Executing DeletePurchasedStockCommand] " + this.toString() );
        
        try {
            model.deleteStock(stockToDelete);
        } catch (StockNotFoundException tnfe) {
            assert false : "The target task cannot be missing";
        }
        
        return new CommandResult(String.format(MESSAGE_DELETE_PURCHASED_STOCK_SUCCESS, stockToDelete));
    }

    private boolean outOfBounds(int listSize){
        return listSize < lastShownListIndex || lastShownListIndex < 1;
    }
	
	@Override
	public CommandResult undo() {
		BuyStockCommand reverseCommand = new BuyStockCommand(stockToDelete);
		reverseCommand.setData(model);
		
		return reverseCommand.execute();
	}
	
	@Override
	public String toString() {
		return COMMAND_WORD +" "+ this.stockToDelete.getAsText();
	}

}
