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
import seedu.task.commons.events.ui.StockPanelSelectionChangedEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * Panel containing the list of tasks.
 */
public class StockListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(StockListPanel.class);
    private static final String FXML = "StockListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyStock> stockListView;

    public StockListPanel() {
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

    public static StockListPanel load(Stage primaryStage, AnchorPane stockListPlaceHolder,
                                       ObservableList<ReadOnlyStock> stockList) {
        StockListPanel stockListPanel =
                UiPartLoader.loadUiPart(primaryStage, stockListPlaceHolder, new StockListPanel());
        stockListPanel.configure(stockList);
        return stockListPanel;
    }

    private void configure(ObservableList<ReadOnlyStock> stockList) {
        setConnections(stockList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyStock> stockList) {
        stockListView.setItems(stockList);
        stockListView.setCellFactory(listView -> new StockListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        stockListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in stock list panel changed to : '" + newValue + "'");
                raise(new StockPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            stockListView.scrollTo(index);
            stockListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class StockListViewCell extends ListCell<ReadOnlyStock> {

        public StockListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyStock stock, boolean empty) {
            super.updateItem(stock, empty);

            if (empty || stock == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(PurchasedStockCard.load(stock, getIndex() + 1).getLayout());
            }
        }
    }
    
    /** @@author A0121608N
     * public function to get taskListView Node
     *  used in focus traversal 
     */
    public Node getStockListView(){
        return stockListView;
    }
}
