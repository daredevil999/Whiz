package seedu.task.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.task.commons.core.CalendarView;
import seedu.task.commons.core.Config;
import seedu.task.commons.core.GuiSettings;
import seedu.task.commons.events.ui.ExitAppRequestEvent;
import seedu.task.logic.Logic;
import seedu.task.model.UserPrefs;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart {

    private static final String ICON = "/images/whiz.png";
    private static final String FXML = "MainWindow.fxml";
    public static final int MIN_HEIGHT = 500;
    public static final int MIN_WIDTH = 800;
	

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private EventListPanel eventListPanel;
    private StockListPanel stockListPanel;
    private ResultDisplay resultDisplay;
    private StatusBarFooter statusBarFooter;
    private CommandBox commandBox;
    private Config config;
    private UserPrefs userPrefs;
    //private CalendarPanel calendarPanel;

    // Handles to elements of this Ui container
    private VBox rootLayout;
    private Scene scene;

    private String taskBookName;

    @FXML
    private AnchorPane eventListPanelPlaceholder;

    @FXML
    private AnchorPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private AnchorPane stockListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;

    @FXML
    private AnchorPane statusbarPlaceholder;
    
//    @FXML
//    private AnchorPane calendarPlaceholder;

    //@@author A0121608N
    // focus variables
    private int focusNumber;
    private ArrayList<Node> focusElements = new ArrayList<Node>();
    //@@author

    public MainWindow() {
        super();
    }
    
    /** @@author A0121608N
     *  sets the node and establish 2 event filters to bypass default focus traversal hierarchy
     *  and to handle mouse selection of windows
     */
    @Override
    public void setNode(Node node) {
        rootLayout = (VBox) node;

        // adds an event filter to bypass default focus traversal hierarchy
        rootLayout.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    event.consume();
                    focusNumber++;
                    if(focusNumber > 3){
                        focusNumber = 0;
                    }
                    focusElements.get(focusNumber).requestFocus();
                }
            }
        });
       
        // adds an event filter to handle mouse selection 
        rootLayout.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i=0;i<3;i++){
                    Node element = focusElements.get(i);
                    if(element.isFocused()){
                        focusNumber = i;
                    }
                }
            }
        });
    }
    //@@author

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    public static MainWindow load(Stage primaryStage, Config config, UserPrefs prefs, Logic logic) {

        MainWindow mainWindow = UiPartLoader.loadUiPart(primaryStage, new MainWindow());
        mainWindow.configure(config.getAppTitle(), config.getTaskBookName(), config, prefs, logic);
        return mainWindow;
    }


    private void configure(String appTitle, String taskBookName, Config config, UserPrefs prefs,
                           Logic logic) {

        //Set dependencies
        this.logic = logic;
        this.taskBookName = taskBookName;
        this.config = config;
        this.userPrefs = prefs;

        //Configure the UI
        setTitle(appTitle);
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        setAccelerators();
    }

    private void setAccelerators() {
        helpMenuItem.setAccelerator(KeyCombination.valueOf("F1"));
    }

    //@@author A0121608N
    void fillInnerParts() {
        //AquaFx.style();
//    	calendarPanel = CalendarPanel.load(primaryStage, getCalendarPlaceholder(), logic.getAllEvents(), logic.getAllTasks());
    	eventListPanel = EventListPanel.load(primaryStage, getEventListPlaceholder(), logic.getFilteredEventList());
        stockListPanel = StockListPanel.load(primaryStage, getStockListPlaceholder(), logic.getFilteredTaskList());
        resultDisplay = ResultDisplay.load(primaryStage, getResultDisplayPlaceholder());
        statusBarFooter = StatusBarFooter.load(primaryStage, getStatusbarPlaceholder(), config.getTaskBookFilePath());
        commandBox = CommandBox.load(primaryStage, getCommandBoxPlaceholder(), resultDisplay, logic);
        
        // define focus variables
        focusNumber = 0;
        focusElements.add(commandBox.getCommandTextField());
        focusElements.add(resultDisplay.getResultDisplayArea());
        focusElements.add(stockListPanel.getTaskListView());
        focusElements.add(eventListPanel.getEventListView());

    }
    //@@author

//    private AnchorPane getCalendarPlaceholder() {
//		return calendarPlaceholder;
//	}

	private AnchorPane getCommandBoxPlaceholder() {
        return commandBoxPlaceholder;
    }

    private AnchorPane getStatusbarPlaceholder() {
        return statusbarPlaceholder;
    }

    private AnchorPane getResultDisplayPlaceholder() {
        return resultDisplayPlaceholder;
    }

    public AnchorPane getStockListPlaceholder() {
        return stockListPanelPlaceholder;
    }
    
    public AnchorPane getEventListPlaceholder() {
        return eventListPanelPlaceholder;
    }

    public void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the default size based on user preferences.
     */
    protected void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    public GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
    }

    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = HelpWindow.load(primaryStage);
        helpWindow.show();
    }

    public void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }

    public StockListPanel getStockListPanel() {
        return this.stockListPanel;
    }
    
    public EventListPanel getEventListPanel() {
        return this.eventListPanel;
    }
    
//    public CalendarPanel getCalendarPanel() {
//        return this.calendarPanel;
//    }
//    
//	public void updateCalendar(List<ReadOnlyEvent> eventList, List<ReadOnlyStock> taskList) {
//		this.calendarPanel.refresh(eventList,taskList);
//	}
//
//	public void updateCalendarView(LocalDateTime displayedDateTime, CalendarView calendarViewMode) {
//		this.calendarPanel.updateCalendarMode(calendarViewMode);
//		this.calendarPanel.updateCalendarShownPeriod(displayedDateTime);
//	}

}
