package seedu.task.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyStockManager;

/**
 * Represents a storage for {@link seedu.task.model.StockManager}.
 */
public interface StockManagerStorage {

	/**
	 * Returns the file path of the data file.
	 */
	String getStockManagerFilePath();

	/**
	 * Returns TaskBook data as a {@link ReadOnlyStockManager}. Returns
	 * {@code Optional.empty()} if storage file is not found.
	 * 
	 * @throws DataConversionException
	 *             if the data in storage is not in the expected format.
	 * @throws IOException
	 *             if there was any problem when reading from the storage.
	 */
	Optional<ReadOnlyStockManager> readStockManager() throws DataConversionException, IOException;

	/**
	 * @see #getStockManagerFilePath()
	 */
	Optional<ReadOnlyStockManager> readStockManager(String filePath) throws DataConversionException, IOException;

	/**
	 * Saves the given {@link ReadOnlyStockManager} to the storage.
	 * 
	 * @param stockManager
	 *            cannot be null.
	 * @throws IOException
	 *             if there was any problem writing to the file.
	 */

	void saveStockManager(ReadOnlyStockManager stockManager) throws IOException;

	/**
	 * @see #saveStockManager(ReadOnlyStockManager)
	 */
	
	void saveStockManager(ReadOnlyStockManager stockManager, String filePath) throws IOException;

	
}
