package seedu.task.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.task.commons.events.model.StockManagerChangedEvent;
import seedu.task.commons.events.storage.DataSavingExceptionEvent;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyStockManager;
import seedu.task.model.UserPrefs;
/**
 * API of the Storage component
 */
public interface Storage extends StockManagerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getStockManagerFilePath();

    @Override
    Optional<ReadOnlyStockManager> readStockManager() throws DataConversionException,IOException;

    @Override
    void saveStockManager(ReadOnlyStockManager stockManager) throws IOException;

    /**
     * Saves the current version of the Stock Manager to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleStockManagerChangedEvent(StockManagerChangedEvent abce);

	

}
