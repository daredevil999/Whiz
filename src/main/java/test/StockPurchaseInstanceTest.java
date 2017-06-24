package test;

import static org.junit.Assert.*;

import org.junit.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Price;
import seedu.task.model.item.StockPurchaseInstance;;

public class StockPurchaseInstanceTest {

	@Test
    public void stockPurchaseInstanceTest() throws IllegalValueException {
		Price priceInput = new Price("23.056");
		Date dateInput = new Date("23-6-17");
		int lotsInput = 1200;
		StockPurchaseInstance sp1 = new StockPurchaseInstance(dateInput, priceInput, lotsInput);
        
		assertEquals(sp1.toString(), "Blah");
    }

}
