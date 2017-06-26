package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToTaskListRequestEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.Price;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockPurchaseInstance;
import seedu.task.model.item.UniqueStockList;

//@@author A0127570H
/**
 * Adds a task to the task book.
 * @author kian ming
 */

public class AddStockCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New stock added: %1$s";
	public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the stock manager";

	private final Logger logger = LogsCenter.getLogger(AddStockCommand.class);

	private final Stock toAddStock;
	

	/**
	 * Convenience constructor using raw values.
	 * @throws IllegalValueException
	 *             if any of the raw values are invalid
	 */

	public AddStockCommand(String name, String price, String date, String lots) throws IllegalValueException {
		StockPurchaseInstance toAddInstance = new StockPurchaseInstance(new Date(date), new Price (price), Integer.parseInt(lots));
		this.toAddStock = new Stock(new Name(name), toAddInstance);
	}
	
	public AddStockCommand(ReadOnlyStock t) {
		this.toAddStock = new Stock(t);
	}

	/*
	 * Execute for add task command
	 * Newly added task is to be selected for easy viewing
	 * Done by posting a JumpToTaskListRequestEvent
	 */
	@Override
	public CommandResult execute() {
		assert model != null;
		logger.info("-------[Executing AddStockCommand] " + this.toString() );
		try {
			model.addStock(toAddStock);
			
			UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredTaskList();
			EventsCenter.getInstance().post(new JumpToTaskListRequestEvent(toAddStock, lastShownList.indexOf(toAddStock)));
			return new CommandResult(String.format(MESSAGE_SUCCESS, toAddStock));
		} catch (UniqueStockList.DuplicateStockException e) {
			return new CommandResult(MESSAGE_DUPLICATE_STOCK);
		}
	}

	@Override
	public CommandResult undo() {
		DeleteTaskCommand reverseCommand = new DeleteTaskCommand(toAddStock);
		reverseCommand.setData(model);
		
		return reverseCommand.execute();
	}
	
	@Override
	public String toString() {
		return COMMAND_WORD +" "+ this.toAddStock.getAsText();
	}
	
	

}
