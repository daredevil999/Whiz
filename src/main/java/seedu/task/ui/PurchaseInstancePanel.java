package seedu.task.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.events.ui.PurchaseInstancePanelSelectionChangedEvent;
import seedu.task.commons.events.ui.StockPanelSelectionChangedEvent;
import seedu.task.model.item.ReadOnlyStock;
import seedu.task.model.item.StockPurchaseInstance;

/**
 * Panel containing the list of purchase instances.
 */
public class PurchaseInstancePanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(PurchaseInstancePanel.class);
    private static final String FXML = "PurchaseInstancePanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<StockPurchaseInstance> purchaseInstanceListView;

    public PurchaseInstancePanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static PurchaseInstancePanel load(Stage primaryStage, AnchorPane purchaseInstanceListPlaceHolder,
                                       ObservableList<StockPurchaseInstance> purchaseInstanceList) {
        PurchaseInstancePanel purchaseInstancePanel =
                UiPartLoader.loadUiPart(primaryStage, purchaseInstanceListPlaceHolder, new PurchaseInstancePanel());
        purchaseInstancePanel.configure(purchaseInstanceList);
        return purchaseInstancePanel;
    }

    private void configure(ObservableList<StockPurchaseInstance> purchaseInstanceList) {
        setConnections(purchaseInstanceList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<StockPurchaseInstance> purchaseInstanceList) {
    	purchaseInstanceListView.setItems(purchaseInstanceList);
    	purchaseInstanceListView.setCellFactory(listView -> new PurchaseInstanceListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
    	purchaseInstanceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in stock list panel changed to : '" + newValue + "'");
                raise(new PurchaseInstancePanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
        	purchaseInstanceListView.scrollTo(index);
        	purchaseInstanceListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class PurchaseInstanceListViewCell extends ListCell<StockPurchaseInstance> {

        public PurchaseInstanceListViewCell() {
        }

        @Override
        protected void updateItem(StockPurchaseInstance instance, boolean empty) {
            super.updateItem(instance, empty);

            if (empty || instance == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(PurchaseInstanceCard.load(instance, getIndex() + 1).getLayout());
            }
        }
    }
}
