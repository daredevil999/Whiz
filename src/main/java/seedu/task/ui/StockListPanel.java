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
    private ListView<ReadOnlyStock> taskListView;

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

    public static StockListPanel load(Stage primaryStage, AnchorPane taskListPlaceHolder,
                                       ObservableList<ReadOnlyStock> taskList) {
        StockListPanel taskListPanel =
                UiPartLoader.loadUiPart(primaryStage, taskListPlaceHolder, new StockListPanel());
        taskListPanel.configure(taskList);
        return taskListPanel;
    }

    private void configure(ObservableList<ReadOnlyStock> taskList) {
        setConnections(taskList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyStock> taskList) {
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in task list panel changed to : '" + newValue + "'");
                raise(new StockPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            taskListView.scrollTo(index);
            taskListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class TaskListViewCell extends ListCell<ReadOnlyStock> {

        public TaskListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyStock task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(PurchasedStockCard.load(task, getIndex() + 1).getLayout());
            }
        }
    }
    
    /** @@author A0121608N
     * public function to get taskListView Node
     *  used in focus traversal 
     */
    public Node getTaskListView(){
        return taskListView;
    }
}
