package seedu.task.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Description;
import seedu.task.model.item.Event;
import seedu.task.model.item.EventDuration;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyEvent;

/**
 * JAXB-friendly version of the Event.
 */
public class XmlAdaptedEvent {
	
	@XmlElement(required = true)
	private String name;
	
	@XmlElement
	private String description;
	
	@XmlElement
	private String startDuration;
	
	@XmlElement
    private String endDuration;


    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedEvent() {}

    //@@author A0127570H
    /**
     * Converts a given event into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedEvent
     */
    public XmlAdaptedEvent(ReadOnlyEvent source) {
        name = source.getEvent().fullName;
        description = source.getDescriptionValue();
        startDuration = source.getDuration().getStartTimeAsText();
        endDuration = source.getDuration().getEndTimeAsText();
    }

    /**
     * Converts this jaxb-friendly adapted event object into the model's Event object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event
     */
    public Event toModelType() throws IllegalValueException {

        final Name name = new Name(this.name);
        final Description description = this.description.isEmpty()? null : new Description(this.description);
        final EventDuration eventDuration = new EventDuration(this.startDuration, this.endDuration);
        
        return new Event(name, description, eventDuration);
    }
    
}
