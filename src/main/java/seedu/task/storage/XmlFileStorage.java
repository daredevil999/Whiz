package seedu.task.storage;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.commons.util.XmlUtil;

/**
 * Stores stock manager data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given stock manager data to the specified file.
     */
    public static void saveDataToFile(File file, XmlSerializableStockManager stockManager)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, stockManager);
        } catch (JAXBException e) {
            assert false : "Unexpected exception " + e.getMessage();
        }
    }

    /**
     * Returns task book in the file or an empty task book
     */
    public static XmlSerializableStockManager loadDataFromSaveFile(File file) throws DataConversionException,
                                                                            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableStockManager.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
