package seedu.task.storage;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.task.commons.core.ComponentManager;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.events.model.StockManagerChangedEvent;
import seedu.task.commons.events.storage.DataSavingExceptionEvent;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyStockManager;
import seedu.task.model.UserPrefs;
/**
 * Manages storage of stock manager data in local storage.
 * 
 */
public class StorageManager extends ComponentManager implements Storage {

	
	private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
	private XmlStockManagerStorage stockManagerStorage;
	private JsonUserPrefStorage userPrefStorage;
	private TxtStockCodeStorage

	public StorageManager(String stockManagerFilePath, String userPrefsFilePath) {
		super();
		this.stockManagerStorage = new XmlStockManagerStorage(stockManagerFilePath);
		this.userPrefStorage = new JsonUserPrefStorage(userPrefsFilePath);
	}

	// ================ UserPrefs methods ==============================

	@Override
	public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
		return userPrefStorage.readUserPrefs();
	}

	@Override
	public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
		userPrefStorage.saveUserPrefs(userPrefs);
	}

	// ================ Stock Manager methods ==============================

	@Override
	public String getStockManagerFilePath() {
		return stockManagerStorage.getStockManagerFilePath();
	}

	@Override
	// read from project main directory 
	public Optional<ReadOnlyStockManager> readStockManager() throws DataConversionException, IOException {

		return readStockManager(stockManagerStorage.getStockManagerFilePath());
	}

	@Override
	//read from specified saved file path
	public Optional<ReadOnlyStockManager> readStockManager(String filePath) throws DataConversionException, IOException {
		logger.fine("Attempting to read data from file: " + filePath);
		return stockManagerStorage.readStockManager(filePath);
	}

	@Override
	// save to project main directory
	public void saveStockManager(ReadOnlyStockManager stockManager) throws IOException {
		saveStockManager(stockManager, stockManagerStorage.getStockManagerFilePath());
	}

	@Override
	// save to specified file path
	public void saveStockManager(ReadOnlyStockManager stockManager, String filePath) throws IOException {
		logger.fine("Attempting to write to data file: " + filePath);
		stockManagerStorage.saveStockManager(stockManager, filePath);
	}

	
	@Override
	@Subscribe
	public void handleStockManagerChangedEvent(StockManagerChangedEvent event) {
		logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
		try {
			saveStockManager(event.data);
		} catch (IOException e) {
			raise(new DataSavingExceptionEvent(e));
		}
	}

}
