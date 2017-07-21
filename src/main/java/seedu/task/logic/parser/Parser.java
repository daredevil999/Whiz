package seedu.task.logic.parser;

import seedu.task.logic.commands.Command;
import seedu.task.logic.parser.ArgumentTokenizer.Prefix;
import  seedu.task.model.item.Flag;

/**
 * API of the Parser component
 */

public interface Parser {
    
    public static final Prefix purchasedStockPrefix = new Prefix(Flag.purchasedStockFlag);
    public static final Prefix trackedStockPrefix = new Prefix(Flag.trackedStockFlag);
    public static final Prefix namePrefix = new Prefix(Flag.nameFlag);  
    public static final Prefix pricePrefix = new Prefix(Flag.priceFlag);
    public static final Prefix datePrefix = new Prefix(Flag.dateFlag);
    public static final Prefix lotsPrefix = new Prefix(Flag.lotsFlag);
    public static final Prefix deadlinePrefix = new Prefix(Flag.deadlineFlag);
    public static final Prefix durationStartPrefix = new Prefix(Flag.durationStartFlag);
    public static final Prefix durationEndPrefix = new Prefix(Flag.durationEndFlag);  
    public static final Prefix calendarViewWeekPrefix = new Prefix(Flag.calendarWeek);
    public static final Prefix calendarViewDayPrefix = new Prefix(Flag.calendarDay);
    public static final Prefix allPrefix = new Prefix(Flag.allFlag);
    public static final Prefix taskPresencePrefix = new Prefix(Flag.stockPresenceFlag);
    public static final Prefix eventPresencePrefix = new Prefix(Flag.eventPresenceFlag);
    public static final Prefix powerSearchPrefix = new Prefix(Flag.powerSearchFlag);
    public static final Prefix findKeywordPrefix = new Prefix(Flag.findKeywordFlag);
    
    /**
     * Prepares the command and returns the prepared Command.
     * @param args The arguments as entered by the user.
     * @return the prepared Command for execution.
     */
    public Command prepare(String args);
}
