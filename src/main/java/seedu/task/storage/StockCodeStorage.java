package seedu.task.storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;

public interface StockCodeStorage {
	/**
	 * Returns the file path of the data file.
	 */
	String getStockCodeStorageFilePath();

	Optional<HashMap<String,String>> readStockCodeMap() throws DataConversionException, IOException;

	/**
	 * @see #getTaskManagerFilePath()
	 */
	Optional<HashMap<String,String>> readStockCodeMap(String filepath) throws DataConversionException, IOException;
	
}
