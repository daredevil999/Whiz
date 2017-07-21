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
import seedu.task.commons.events.ui.PStockPanelSelectionChangedEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * Panel containing the list of purchased stocks.
 */
public class PStockListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(PStockListPanel.class);
    private static final String FXML = "PStockListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyStock> pStockListView;

    public PStockListPanel() {
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

    public static PStockListPanel load(Stage primaryStage, AnchorPane pStockListPlaceHolder,
                                       ObservableList<ReadOnlyStock> pStockList) {
        PStockListPanel pStockListPanel = UiPartLoader.loadUiPart(primaryStage, pStockListPlaceHolder, new PStockListPanel());
        pStockListPanel.configure(pStockList);
        return pStockListPanel;
    }

    private void configure(ObservableList<ReadOnlyStock> pStockList) {
        setConnections(pStockList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyStock> pStockList) {
        pStockListView.setItems(pStockList);
        pStockListView.setCellFactory(listView -> new StockListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        pStockListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in purchased stock list panel changed to : '" + newValue + "'");
                raise(new PStockPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            pStockListView.scrollTo(index);
            pStockListView.getSelectionModel().clearAndSelect(index);
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
        return pStockListView;
    }
}
