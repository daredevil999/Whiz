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
	String getTaskBookFilePath();

	/**
	 * Returns TaskBook data as a {@link ReadOnlyStockManager}. Returns
	 * {@code Optional.empty()} if storage file is not found.
	 * 
	 * @throws DataConversionException
	 *             if the data in storage is not in the expected format.
	 * @throws IOException
	 *             if there was any problem when reading from the storage.
	 */
	Optional<ReadOnlyStockManager> readTaskBook() throws DataConversionException, IOException;

	/**
	 * @see #getTaskManagerFilePath()
	 */
	Optional<ReadOnlyStockManager> readTaskBook(String filePath) throws DataConversionException, IOException;

	/**
	 * Saves the given {@link ReadOnlyStockManager} to the storage.
	 * 
	 * @param taskBook
	 *            cannot be null.
	 * @throws IOException
	 *             if there was any problem writing to the file.
	 */

	void saveTaskBook(ReadOnlyStockManager taskBook) throws IOException;

	/**
	 * @see #saveTaskManager(ReadOnlyStockManager)
	 */
	
	void saveTaskBook(ReadOnlyStockManager taskBook, String filePath) throws IOException;

	
}
