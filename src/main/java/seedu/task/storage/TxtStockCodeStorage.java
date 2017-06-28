package seedu.task.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.util.StringUtil;

public class TxtStockCodeStorage {

	private static final Logger logger = LogsCenter.getLogger(TxtStockCodeStorage.class);
	private final static String filePath = "StockCodesAndNames.txt";
	private static final HashMap<String,String> stockCodesNamesMap = getStockCodeNameMap();
	private static final HashMap<String,String> stockNamesCodesMap = getStockNameCodeMap();
	
	
	public TxtStockCodeStorage() {}
	
	public String getStockCodeStorageFilePath() {
		return filePath;
	}

	public static HashMap<String, String> getStockCodeNameMap() {	
		HashMap<String, String> stockCodeMapOptional = new HashMap<>();
		try {
			stockCodeMapOptional = loadCodesNamesToMapFromTxt();
		} catch (IOException e) {
			logger.warning("Failed to load stock codes from text file path : " + filePath);
		}
		return stockCodeMapOptional;
	}

	private static HashMap<String, String> loadCodesNamesToMapFromTxt() throws IOException {
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
	
	public static HashMap<String, String> getStockNameCodeMap() {	
		HashMap<String, String> stockCodeMapOptional = new HashMap<>();
		try {
			stockCodeMapOptional = loadNamesCodesToMapFromTxt();
		} catch (IOException e) {
			logger.warning("Failed to load stock codes from text file path : " + filePath);
		}
		return stockCodeMapOptional;
	}
	
	private static HashMap<String, String> loadNamesCodesToMapFromTxt() throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		HashMap<String, String> stockCodeMapOptional = new HashMap<>();
		
		for (String line; (line = br.readLine()) != null;) {
        	String[] splitStr = line.split("\\;");
        	stockCodeMapOptional.put(splitStr[1].trim(), splitStr[0].trim());    	
    	}
    	br.close();
		return stockCodeMapOptional;
	}
	
	public static boolean isStockCodeValid(String test) {
		return stockCodesNamesMap.containsKey(test);
	}
	
	public static String getStockNameFromCode(String code) {
		return stockCodesNamesMap.get(code);
	}
	
	public static boolean isStockNameValid(String test) {
		return stockNamesCodesMap.containsKey(test);
	}
	
	public static String getStockCodeFromName(String name) {
		return stockNamesCodesMap.get(name);
	}

}
