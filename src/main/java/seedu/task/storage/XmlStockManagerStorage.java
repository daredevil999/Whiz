package seedu.task.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.commons.util.FileUtil;
import seedu.task.model.ReadOnlyStockManager;

/**
 * A class to access TaskBook data stored as an xml file on the hard disk.
 *
 */
public class XmlStockManagerStorage implements StockManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlStockManagerStorage.class);

    private String filePath;

    public XmlStockManagerStorage(String filePath){
        this.filePath = filePath;
    }

    public String getStockManagerFilePath(){
        return filePath;
    }

    /**
     * Similar to {@link #readStockManager()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStockManager> readStockManager(String filePath) throws DataConversionException, FileNotFoundException {
        assert filePath != null;

        File taskBookFile = new File(filePath);

        if (!taskBookFile.exists()) {
            logger.info("TaskBook file "  + taskBookFile + " not found");
            return Optional.empty();
        }

        ReadOnlyStockManager taskBookOptional = XmlFileStorage.loadDataFromSaveFile(new File(filePath));

        return Optional.of(taskBookOptional);
    }

    /**
     * Similar to {@link #saveStockManager(ReadOnlyStockManager)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveStockManager(ReadOnlyStockManager taskBook, String filePath) throws IOException {
        assert taskBook != null;
        assert filePath != null;

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveDataToFile(file, new XmlSerializableStockManager(taskBook));
    }

    @Override
    public Optional<ReadOnlyStockManager> readStockManager() throws DataConversionException, IOException {
        return readStockManager(filePath);
    }

    @Override
    public void saveStockManager(ReadOnlyStockManager taskBook) throws IOException {
        saveStockManager(taskBook, filePath);
    }
}
