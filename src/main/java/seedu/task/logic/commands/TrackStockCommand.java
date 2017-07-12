package seedu.task.logic.commands;

import java.util.logging.Logger;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToStockListRequestEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Candlestick;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.Price;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockCode;
import seedu.task.model.item.StockPurchaseInstance;
import seedu.task.model.item.UniqueStockList;
import seedu.task.storage.TxtStockCodeStorage;


public class TrackStockCommand extends TrackCommand {

    public static final String MESSAGE_SUCCESS = "New stock tracked: %1$s";

	private final Logger logger = LogsCenter.getLogger(TrackStockCommand.class);

	private final Stock toTrackStock;
	
	/**
	 * Convenience constructor using raw values.
	 * @throws IllegalValueException
	 *             if any of the raw values are invalid
	 */

	public TrackStockCommand(String name) throws IllegalValueException {
		Candlestick candlestickInstance = null;
		String code = TxtStockCodeStorage.getStockCodeFromName(name);
		this.toTrackStock = new Stock(new Name(name), new StockCode(code), candlestickInstance);
	}
	
	public TrackStockCommand(ReadOnlyStock t) {
		this.toTrackStock = new Stock(t);
	}

	/*
	 * Execute for track stockk command
	 * Done by posting a JumpToTaskListRequestEvent
	 */
	@Override
	public CommandResult execute() {
		assert model != null;
		logger.info("-------[Executing TrackStockCommand] " + this.toString() );
		model.addStock(toTrackStock);
			
		UnmodifiableObservableList<ReadOnlyStock> lastShownList = model.getFilteredStockList();
		EventsCenter.getInstance().post(new JumpToStockListRequestEvent(toTrackStock, lastShownList.indexOf(toTrackStock)));
		return new CommandResult(String.format(MESSAGE_SUCCESS, toTrackStock.getPurchasedStockAsText()));
	}

	@Override
	public CommandResult undo() {
		DeletePurchasedStockCommand reverseCommand = new DeletePurchasedStockCommand(toTrackStock);
		reverseCommand.setData(model);
		
		return reverseCommand.execute();
	}
	
	@Override
	public String toString() {
		return COMMAND_WORD +" "+ this.toTrackStock.getAsText();
	}
	
	

}
