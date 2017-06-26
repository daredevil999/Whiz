package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.task.commons.exceptions.EmptyValueException;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.AddEventCommand;
import seedu.task.logic.commands.AddStockCommand;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.IncorrectCommand;

//@@author A0127570H
/**
 * Responsible for validating and preparing the arguments for AddCommand execution
 * @author kian ming
 */

public class AddParser implements Parser {
    
    private String name;
    private Optional <String> price;
    private Optional <String> lots;
    private Optional <String> date;
    
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
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        
        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer(pricePrefix, datePrefix, lotsPrefix);
        argsTokenizer.tokenize(args);
        
        try {           
            getTokenizerValue(argsTokenizer);
            
            return new AddStockCommand(name ,
            		price.orElse(""), date.orElse(""),lots.orElse(""));
//            		description.orElse(""), deadline.orElse(""));             
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        } catch (EmptyValueException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /*
     * To get the values according to each field prefix
     */
    private void getTokenizerValue(ArgumentTokenizer argsTokenizer) throws EmptyValueException {
        name = argsTokenizer.getPreamble().get();
        price = argsTokenizer.getValue(pricePrefix);
        date = argsTokenizer.getValue(datePrefix);
        lots = argsTokenizer.getValue(lotsPrefix);
    } 
    
}
