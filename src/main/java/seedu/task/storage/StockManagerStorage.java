package seedu.task.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyManager;

/**
 * Represents a storage for {@link seedu.task.model.TaskBook}.
 */
public interface StockManagerStorage {

	/**
	 * Returns the file path of the data file.
	 */
	String getTaskBookFilePath();

	/**
	 * Returns TaskBook data as a {@link ReadOnlyManager}. Returns
	 * {@code Optional.empty()} if storage file is not found.
	 * 
	 * @throws DataConversionException
	 *             if the data in storage is not in the expected format.
	 * @throws IOException
	 *             if there was any problem when reading from the storage.
	 */
	Optional<ReadOnlyManager> readTaskBook() throws DataConversionException, IOException;

	/**
	 * @see #getTaskManagerFilePath()
	 */
	Optional<ReadOnlyManager> readTaskBook(String filePath) throws DataConversionException, IOException;

	/**
	 * Saves the given {@link ReadOnlyManager} to the storage.
	 * 
	 * @param taskBook
	 *            cannot be null.
	 * @throws IOException
	 *             if there was any problem writing to the file.
	 */

	void saveTaskBook(ReadOnlyManager taskBook) throws IOException;

	/**
	 * @see #saveTaskManager(ReadOnlyManager)
	 */
	
	void saveTaskBook(ReadOnlyManager taskBook, String filePath) throws IOException;

	
}
