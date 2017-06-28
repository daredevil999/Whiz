package seedu.task.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;

public class TxtStockCodeStorage implements StockCodeStorage {

	private String filePath;
	
	public TxtStockCodeStorage(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String getStockCodeStorageFilePath() {
		return filePath;
	}

	@Override
	public Optional<HashMap<String, String>> readStockCodeMap(String filepath)
			throws IOException {
		assert filePath != null;
		
		HashMap<String, String> stockCodeMapOptional = loadCodesFromTxt ();
		return Optional.of(stockCodeMapOptional);
	}

	private HashMap<String, String> loadCodesFromTxt() throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		HashMap<String, String> stockCodeMapOptional = new HashMap<>();
		
		for (String line; (line = br.readLine()) != null;) {
        	String[] splitStr = line.split("\\;");
        	stockCodeMapOptional.put(splitStr[0], splitStr[1]);    	
    	}
    	br.close();
		return stockCodeMapOptional;
	}

	@Override
	public Optional<HashMap<String, String>> readStockCodeMap() throws DataConversionException, IOException {
		return readStockCodeMap(filePath);
	}

}
