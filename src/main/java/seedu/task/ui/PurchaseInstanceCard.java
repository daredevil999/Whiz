package seedu.task.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.StockPurchaseInstance;


public class PurchaseInstanceCard extends UiPart{

    private static final String FXML = "PurchaseInstanceListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label price;
    @FXML
    private Label lots;
    @FXML
    private Label index;
    

    private StockPurchaseInstance instance;
    private int displayedIndex;

    public PurchaseInstanceCard(){

    }

    public static PurchaseInstanceCard load(StockPurchaseInstance instance, int displayedIndex){
        PurchaseInstanceCard card = new PurchaseInstanceCard();
        card.instance = instance;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author-A0127570H
    @FXML
    public void initialize() {
        date.setText(instance.getPurchaseDate().getDate() + "  ");
        price.setText(instance.getPurchasePrice().toString() + "  ");
        lots.setText(instance.getPurchaseLots() + "  ");
        index.setText(displayedIndex + ". "); 
        //setStyleClass();
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
