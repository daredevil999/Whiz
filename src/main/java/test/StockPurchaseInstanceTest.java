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
		Date dateInput = new Date("6-23-17");
		int lotsInput = 1200;
		StockPurchaseInstance sp1 = new StockPurchaseInstance(dateInput, priceInput, lotsInput);
        String expected = " Purchase Date: 2017-06-23 Purchase Price: 23.056 Purchase Lots: 1200";
		assertEquals(sp1.toString(), expected);
    }

}
