package seedu.task.ui;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
//@@author A0144702N-reused
public class EventCard extends UiPart {
    private static final String FXML = "EventListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label description;
    @FXML
    private Label duration;
    

    private ReadOnlyEvent event;
    private int displayedIndex;


    public static EventCard load(ReadOnlyEvent event, int displayedIndex){
        EventCard card = new EventCard();
        card.event = event;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author-A0127570H
    @FXML
    public void initialize() {
        name.setText(event.getNameWithStatus());
        index.setText(displayedIndex + ". ");
        initialiseDescription();
        duration.setText(event.getDuration().toString().trim());
        setStyleClass();
    }

    private void initialiseDescription() {
        description.setText(event.getDescriptionToString().trim());
        if (event.getDescription().isPresent()) {
            description.setManaged(true);
        } else {
            description.setManaged(false);
        }
    }
    
    //Adds the lavender colour to the background if the task status is completed
    private void setStyleClass() {
        if (event.isEventCompleted()) {
            cardPane.getStyleClass().add("status-complete");
        } else if (isDueToday(event)) {
        	cardPane.getStyleClass().add("status-today");
        }
    }

    //@@author
    
	private boolean isDueToday(ReadOnlyEvent event) {
		LocalDateTime eventTime = event.getDuration().getStartTime();
		return eventTime.getDayOfYear() == LocalDateTime.now().getDayOfYear();
	}
	
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
