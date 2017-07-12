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
import seedu.task.logic.commands.TrackCommand;
import seedu.task.logic.commands.TrackStockCommand;
import seedu.task.storage.TxtStockCodeStorage;

/**
 * Responsible for validating and preparing the arguments for TrackCommand execution
 */

public class TrackParser implements Parser {
    
    private String name;
    
    public static final String MESSAGE_INCORRECT_STOCK_CODE_INPUT = "Incorrect stock code input!";
    
    public TrackParser() {}
    
    /**
     * Parses arguments in the context of the add task or event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    @Override
    public Command prepare(String args){
        
        if (args.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE));
        }        
        try {
        	args = args.trim();
        	if (args.length() < 5) {
        		this.name = TxtStockCodeStorage.getStockNameFromCode(args).trim();
        	}
            return new TrackStockCommand(name);             
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    } 
    
}
