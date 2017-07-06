package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.item.Date;
import seedu.task.model.item.Name;
import seedu.task.model.item.Price;
import seedu.task.model.item.Stock;
import seedu.task.model.item.StockCode;
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
	
	@Test
    public void stockPurchaseInstanceTest2() throws IllegalValueException {
		Price priceInput = new Price("0.056");
		Date dateInput = new Date("6-23-17");
		int lotsInput = 1200;
		StockPurchaseInstance sp1 = new StockPurchaseInstance(dateInput, priceInput, lotsInput);
        String expected = " Purchase Date: 2017-06-23 Purchase Price: 0.056 Purchase Lots: 1200";
		assertEquals(sp1.toString(), expected);
    }
	
	@Test
	public void stockSummaryTest() throws IllegalValueException {
		ArrayList<StockPurchaseInstance> listInput = new ArrayList<>();
		StockPurchaseInstance inst1 = new StockPurchaseInstance(new Date("6-23-17"), new Price("23.056"), 1200);
		StockPurchaseInstance inst2 = new StockPurchaseInstance(new Date("6-24-17"), new Price("22.555"), 1000);
		StockPurchaseInstance inst3 = new StockPurchaseInstance(new Date("6-25-17"), new Price("21.000"), 200);
		listInput.add(inst1);
		listInput.add(inst2);
		listInput.add(inst3);
		Stock ts1 = new Stock (new Name("DBS GROUP HOLDINGS LTD"), new StockCode ("D05"), listInput, null);
		
		assertEquals(ts1.toString()," Name: DBS GROUP HOLDINGS LTD | D05"
				+ System.getProperty("line.separator")
				+ "Instance 1:  Purchase Date: 2017-06-23 Purchase Price: 23.056 Purchase Lots: 1200"
				+ System.getProperty("line.separator")
				+ "Instance 2:  Purchase Date: 2017-06-24 Purchase Price: 22.555 Purchase Lots: 1000"
				+ System.getProperty("line.separator")
				+ "Instance 3:  Purchase Date: 2017-06-25 Purchase Price: 21.0 Purchase Lots: 200");
		
		int expectedTotalLots = 2400;
		assertEquals(expectedTotalLots, ts1.getTotalStockPurchaseLots());
		
		int expectedAveragePrice = (23056 * 1200) + (22555 * 1000) + (21000 * 200);
		expectedAveragePrice /= expectedTotalLots;
		int actualPrice = ts1.getAverageStockPurchasePrice().getPriceValueInInt();
		assertEquals(expectedAveragePrice, actualPrice);
		
		double expectedAveragePriceInDouble = (double) expectedAveragePrice / 1000;
		double actualPriceInDouble = ts1.getAverageStockPurchasePrice().getPriceValueInDouble();
		assertEquals(expectedAveragePriceInDouble,actualPriceInDouble,0.0001);
		
	}
	

}
