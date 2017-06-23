package seedu.task.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Description;
import seedu.task.model.item.Name;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.Stock;

/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedStock {
	
	@XmlElement(required = true)
	private String name;

//    @XmlElement
//    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedStock() {}

    //@@author A0127570H
    /**
     * Converts a given task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedStock(ReadOnlyStock source) {
        name = source.getStockName().fullName;
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     * 	- if a description or deadline is a string, make it null.
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Stock toModelType() throws IllegalValueException {

        final Name name = new Name(this.name);
        
        return new Stock(name);
    }
}
