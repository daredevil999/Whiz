package seedu.task.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.util.StringUtil;

public class TxtStockCodeStorage {

	private static final Logger logger = LogsCenter.getLogger(TxtStockCodeStorage.class);
	private final static String filePath = "StockCodesAndNames.txt";;
	
	public TxtStockCodeStorage() {}
	
	public String getStockCodeStorageFilePath() {
		return filePath;
	}

	public static HashMap<String, String> readStockCodeMap() {
		
		HashMap<String, String> stockCodeMapOptional = new HashMap<>();
		try {
			stockCodeMapOptional = loadCodesFromTxt();
		} catch (IOException e) {
			logger.warning("Failed to load stock codes from text file path : " + filePath);
		}
		return stockCodeMapOptional;
	}

	private static HashMap<String, String> loadCodesFromTxt() throws IOException {
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

}
