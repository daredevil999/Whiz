package seedu.task.logic.commands;

//@@author A0127570H
/**
 * Abstract class to represent generic edit operations for task and event.  
 * @author kian ming
 */

public abstract class EditCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "edit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n"
            + "Edits a task or event in the latest displayed list.\n\n"
            + "Editing a task.\n"
            + "Parameters: LIST_TYPE INDEX [/name NEW_NAME] [/desc NEW_DESCRIPTION] [/by NEW_DEADLINE]\n"
            + "Example: "+ COMMAND_WORD + " /t 1 /desc Complete up to pg 24 of notes\n" 
            + "To remove the deadline in the task, use the remove flag 'rm'\n"
            + "Example: "+ COMMAND_WORD + " /t 1 /by rm\n\n"
            + "Editing an event. \n"
            + "Parameters: LIST_TYPE INDEX [/name NEW_NAME] [/desc NEW_DESCRIPTION] [/from NEW_START_DURATION] [/to NEW_END_DURATION]\n" 
            + "Example: "+ COMMAND_WORD + " /e 4 /from 7pm tomorrow";
    
    private static int targetIndex; //starts from index 1
    
    /**
     * Executes the command and returns the result message.
     * @return feedback message of the operation result for display
     */
    public abstract CommandResult execute();
    
    public static void setTargetIndex(int index) {
        targetIndex = index;
    }
    
    /**
     * @return targetIndex which has been offset by 1 for use
     */    
    public static int getTargetIndex() {
        return targetIndex - 1;
    }
}
