package test;

import static org.junit.Assert.*;

import org.junit.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Price;;

public class PriceTest {

	@Test
    public void price_check() throws IllegalValueException {
        Price d1 = new Price("23.00");
        Price d2 = new Price("23");
        

        assertEquals(d1,d2);
        assertEquals(d1,d1);
        
        try {
        	Price d4 = new Price("23.12345");
        } catch (IllegalValueException e) {
        	assertEquals(e.getMessage(), Price.MESSAGE_PRICE_CONSTRAINTS);
        }
        
    }

}
