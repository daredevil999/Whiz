package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.task.commons.exceptions.EmptyValueException;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.exceptions.MissingValueException;
import seedu.task.logic.commands.BuyCommand;
import seedu.task.logic.commands.BuyStockCommand;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.IncorrectCommand;
import seedu.task.storage.TxtStockCodeStorage;

//@@author A0127570H
/**
 * Responsible for validating and preparing the arguments for AddCommand execution
 * @author kian ming
 */

public class AddParser implements Parser {
    
    private String name;
    private String price;
    private String lots;
    private String date;
    
    public static final String MESSAGE_MISSING_PRICE_VALUE = "Price value is missing!"; 
    public static final String MESSAGE_MISSING_DATE_VALUE = "Date input is missing!"; 
    public static final String MESSAGE_MISSING_LOTS_VALUE = "Number of lots is missing!";
    public static final String MESSAGE_INCORRECT_STOCK_CODE_INPUT = "Incorrect stock code input!";
    
    public AddParser() {}
    
    /**
     * Parses arguments in the context of the add task or event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    @Override
    public Command prepare(String args){
        
        if (args.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BuyCommand.MESSAGE_USAGE));
        }
        
        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer(pricePrefix, datePrefix, lotsPrefix);
        argsTokenizer.tokenize(args);
        
        try {           
            getTokenizerValue(argsTokenizer);
            
            return new BuyStockCommand(name, price, date, lots);
//            		description.orElse(""), deadline.orElse(""));             
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        } catch (EmptyValueException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingValueException e) {
			return new IncorrectCommand(e.getMessage());
		}
    }

    /*
     * To get the values according to each field prefix
     */
    private void getTokenizerValue(ArgumentTokenizer argsTokenizer) throws EmptyValueException, MissingValueException {
        boolean temp = false;
        StringBuilder message = new StringBuilder();
    	String nameOrCode = argsTokenizer.getPreamble().get().trim();
    	if (nameOrCode.length() < 5) {
    		this.name = TxtStockCodeStorage.getStockNameFromCode(nameOrCode).trim();
    	} else {
    		this.name = nameOrCode;
    	}
    	
        if (argsTokenizer.getValue(pricePrefix).isPresent()) {
        	price = argsTokenizer.getValue(pricePrefix).get();
        } else {
        	temp = true;
        	message.append(MESSAGE_MISSING_PRICE_VALUE)
        			.append(System.getProperty("line.separator"));
        }
        if (argsTokenizer.getValue(datePrefix).isPresent()) {
        	date = argsTokenizer.getValue(datePrefix).get();
        } else {
        	temp = true;
        	message.append(MESSAGE_MISSING_DATE_VALUE)
        			.append(System.getProperty("line.separator"));
        }
        if (argsTokenizer.getValue(lotsPrefix).isPresent()) {
        	lots = argsTokenizer.getValue(lotsPrefix).get();
        } else {
        	temp = true;
        	message.append(MESSAGE_MISSING_LOTS_VALUE)
        			.append(System.getProperty("line.separator"));
        }
        if (temp) {
        	throw new MissingValueException(message.toString());
        }
    } 
    
}
