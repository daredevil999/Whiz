package seedu.task.model.item;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import seedu.task.commons.util.CollectionUtil;

/**
 * Represents a event in the task book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Event implements ReadOnlyEvent {

    private Name name;
    private Description description;
    private EventDuration eventDuration;

    /**
     * Name and event duration must be present and not null.
     */
    
    public Event(Name name, Description description, EventDuration eventDuration) {
        assert !CollectionUtil.isAnyNull(name, eventDuration);
        this.name = name;
        this.description = description;
        this.eventDuration = eventDuration;
    }
    
    /**
     * Copy constructor.
     */
    public Event(ReadOnlyEvent source) {
        this(source.getEvent(), source.getDescription().orElse(null), source.getDuration());
    }
    
    /**
     * Return if an event has passed by comparing its endTime to the current time.
     * @return true if event passed; false if otherwise. 
     */
    @Override
	public boolean isEventCompleted() {
		return !(getDuration().getEndTime().isAfter(LocalDateTime.now()));
	}

    @Override
    public Name getEvent() {
        return name;
    }

    @Override
    public Optional<Description> getDescription() {
        return Optional.ofNullable(this.description);
    }
    
    @Override
    public EventDuration getDuration() {
        return eventDuration;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyEvent // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyEvent) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return getAsText();
    }
    
    //@@author A0144702N
    /**
	 * Sort duration from earliest to latest
	 * @param o
	 * @return
	 */
	public int sortAsc(Event o) {
		return this.getDuration().compareTo(o.getDuration());
	}

	public static Comparator<? super Event> getAscComparator() {
		//first by duration
		Comparator<Event> byStartTime = (e1, e2) -> e1.getDuration().compareTo(e2.getDuration());
		
		//then by name
		Comparator<Event> byName = (e1, e2) -> e1.getEvent().compareTo(e2.getEvent());
		
		return byStartTime.thenComparing(byName);
		
	} 
	
}
