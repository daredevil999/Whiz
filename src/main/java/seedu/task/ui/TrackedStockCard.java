package seedu.task.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.task.model.item.Candlestick;
import seedu.task.model.item.PriceChange;
import seedu.task.model.item.ReadOnlyStock;


public class TrackedStockCard extends UiPart{

    private static final String FXML = "TStockListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label code;
    @FXML
    private Label index;
    @FXML
    private Label high;
    @FXML
    private Label low;
    @FXML
    private Label open;
    @FXML
    private Label close;
    @FXML
    private Label absoluteChange;

    private ReadOnlyStock stock;
    private int displayedIndex;

    public TrackedStockCard(){

    }

    public static TrackedStockCard load(ReadOnlyStock stock, int displayedIndex){
        TrackedStockCard card = new TrackedStockCard();
        card.stock = stock;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author-A0127570H
    @FXML
    public void initialize() {
        name.setText(stock.getStockName().fullName + "  ");
        code.setText("[" + stock.getStockCode().code + "]");
        index.setText(displayedIndex + ". "); 
        intialiseLatestCandlestick();
    }
    
    private void intialiseLatestCandlestick() {
    	if (stock.getLatestCandlestick().isPresent()) {
    		Candlestick stick = stock.getLatestCandlestick().get();
    		
    		high.setText("High: " + stick.getHighPrice());
    		low.setText("Low: " + stick.getLowPrice());
    		open.setText("Open: " + stick.getOpenPrice());
    		close.setText("Close: " + stick.getClosePrice());
    		absoluteChange.setText("Change: " + stick.getPriceChange());
    		setPriceChangeStyleClass(stick.getPriceChange());
    		
    		setCandlestickManaged(true);
	    } else {
	    	setCandlestickEmpty();
    		setCandlestickManaged(false);
	    }	
	}

	private void setCandlestickEmpty() {
		high.setText("");
		low.setText("");
		open.setText("");
		close.setText("");
		absoluteChange.setText("");
	}

	private void setCandlestickManaged(boolean value) {
		high.setManaged(value);    		
		low.setManaged(value);
		open.setManaged(value);
		close.setManaged(value);
		absoluteChange.setManaged(value);
	}
    
//	private void initialiseDeadline() {
//        deadline.setText(task.getDeadlineToString().trim());
//        if (task.getPurchaseDate().isPresent()) {
//            deadline.setManaged(true);
//        } else {
//            deadline.setManaged(false);
//        }
//    }
//
//    private void initialiseDescription() {
//        description.setText(task.getDescriptionToString().trim());
//        if (task.getDescription().isPresent()) {
//            description.setManaged(true);
//        } else {
//            description.setManaged(false);
//        }
//    }

    private void setPriceChangeStyleClass(PriceChange change) {
        if (change.isSignPositive()) {
        	absoluteChange.getStyleClass().add("cell_normal_green_label");
        } else {
        	absoluteChange.getStyleClass().add("cell_normal_red_label");
        }
    }
    //@@author
    
    //@@author A0144702N
//    private boolean isOverdue(ReadOnlyStock task) {
//		return task.getPurchaseDate().isPresent() 
//				&& task.getPurchaseDate().get().getTime().isBefore(LocalDateTime.now());
//	}
//
//	private boolean isDueToday(ReadOnlyStock task) {
//		if(task.getTaskStatus() || !task.getPurchaseDate().isPresent()) {
//			return false;
//		}
//		LocalDateTime taskDeadline = task.getPurchaseDate().get().getTime();
//		return taskDeadline.getDayOfYear() == LocalDateTime.now().getDayOfYear();
//	}

	public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
