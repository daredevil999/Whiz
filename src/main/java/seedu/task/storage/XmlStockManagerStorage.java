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

        File stockManagerFile = new File(filePath);

        if (!stockManagerFile.exists()) {
            logger.info("StockManager file "  + stockManagerFile + " not found");
            return Optional.empty();
        }

        ReadOnlyStockManager stockManagerOptional = XmlFileStorage.loadDataFromSaveFile(new File(filePath));

        return Optional.of(stockManagerOptional);
    }

    /**
     * Similar to {@link #saveStockManager(ReadOnlyStockManager)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveStockManager(ReadOnlyStockManager stockManager, String filePath) throws IOException {
        assert stockManager != null;
        assert filePath != null;

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveDataToFile(file, new XmlSerializableStockManager(stockManager));
    }

    @Override
    public Optional<ReadOnlyStockManager> readStockManager() throws DataConversionException, IOException {
        return readStockManager(filePath);
    }

    @Override
    public void saveStockManager(ReadOnlyStockManager stockManager) throws IOException {
        saveStockManager(stockManager, filePath);
    }
}
