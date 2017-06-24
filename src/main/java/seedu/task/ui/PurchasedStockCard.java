package seedu.task.ui;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.task.model.item.ReadOnlyStock;


public class PurchasedStockCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
//    @FXML
//    private Label description;
//    @FXML
//    private Label deadline;
    

    private ReadOnlyStock task;
    private int displayedIndex;

    public PurchasedStockCard(){

    }

    public static PurchasedStockCard load(ReadOnlyStock task, int displayedIndex){
        PurchasedStockCard card = new PurchasedStockCard();
        card.task = task;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author-A0127570H
    @FXML
    public void initialize() {
        name.setText(task.getStockName().fullName);
        index.setText(displayedIndex + ". "); 
        //setStyleClass();
    }
//
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

    //Adds the lavender colour to the background if the task status is completed
//    private void setStyleClass() {
//    	//if status-complete
//        if (task.getTaskStatus()) {
//            cardPane.getStyleClass().add("status-complete");
//        } else if (isOverdue(task)) {
//        	cardPane.getStyleClass().add("status-overdue");
//        }else if(isDueToday(task)) {
//        	cardPane.getStyleClass().add("status-today");
//        } 
//
//    }
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
