package seedu.task.logic;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.task.commons.core.ComponentManager;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Status;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.CommandResult;
import seedu.task.logic.commands.UndoableCommand;
import seedu.task.logic.parser.ParserManager;
import seedu.task.model.Model;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.storage.Storage;

/**
 * The main LogicManager of dowat.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final ParserManager parser;
    private UndoableCommandHistory commandList;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new ParserManager();
        this.commandList = new UndoableCommandHistory();
    }
    //@@author A0144702N
    @Override
    public CommandResult execute(String commandText) {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        if(command instanceof UndoableCommand) {
        	UndoableCommand undoableCommand = (UndoableCommand) command;
        	commandList.add(undoableCommand);
        }
        command.setData(model);
        command.setCommandHistory(commandList);
        
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyStock> getFilteredTaskList() {
    	model.updateFilteredTaskListToShowWithStatus(Status.INCOMPLETED);
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<ReadOnlyEvent> getFilteredEventList() {
    	model.updateFilteredEventListToShowWithStatus(Status.INCOMPLETED);
        return model.getFilteredEventList();
    }
    
    @Override
    public List<ReadOnlyEvent> getAllEvents() {
    	return model.getTaskBook().getEventList();
    }
	
    @Override
	public List<ReadOnlyStock> getAllTasks() {
		return model.getTaskBook().getStockList();
	}
    
    
}
