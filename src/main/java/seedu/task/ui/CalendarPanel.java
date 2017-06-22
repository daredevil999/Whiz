package seedu.task.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaysFromDisplayedSkin;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.LocalDateTimeRange;
import seedu.task.commons.core.CalendarView;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.CalendarUnsyncException;
import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyTask;

//@@author A0144702N

/**
 * The Calendar window controller
 * 		Responsible for loading the calendar
 * 		Updating the calendar view 
 * @author xuchen
 *
 */
public class CalendarPanel extends UiPart {
	private static final String CALENDAR_UNSYC_MESSAGE = "Calendar is unsync";
	private static final String CALENDAR_VIEW_ID = "calendar";
	private static final int DEFAULT_BEFORE = -1;
	private static final int DEFAULT_AFTER = 5;
	private static final double DEFAULT_WEEK_VIEW_DAYS= 3.0;
	private Agenda agenda;
	private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);
	private AnchorPane placeHolderPane;
	private final CalendarHelper calHelper;
	
	

	public CalendarPanel() {
		agenda = new Agenda();
		calHelper = CalendarHelper.getInstance();
	}

	public static CalendarPanel load(Stage primaryStage, AnchorPane calendarPlaceHolder,
			List<ReadOnlyEvent> eventList, List<ReadOnlyTask> taskList) {
		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.setupCalendar(primaryStage, calendarPlaceHolder);
		calendarPanel.configure(eventList, taskList);
		return calendarPanel;
	}

	private void setupCalendar(Stage primaryStage, AnchorPane calendarPlaceHolder) {
		logger.info("Setting up Calendar panel...");
		
		setStage(primaryStage);
		setPlaceholder(calendarPlaceHolder);
		setBoundary();
		setWeekView(DEFAULT_BEFORE, DEFAULT_AFTER);
		agenda.setAllowDragging(false);
		agenda.setDisplayedLocalDateTime(LocalDateTime.now());
		resetCallBack();
		addToPlaceHodler();
	}
	
	/**
	 * Set up the week view by setting the default value for the sliders.
	 * @param before
	 * @param after
	 */
	private void setWeekView(int before, int after) {
		AgendaDaysFromDisplayedSkin skin = new AgendaDaysFromDisplayedSkin(this.agenda);
		skin.setDaysBeforeFurthest(before);
		skin.setDaysAfterFurthest(after);
		Slider slider = (Slider)this.agenda.lookup("#daysAfterSlider");
		slider.setValue(DEFAULT_WEEK_VIEW_DAYS);
		this.agenda.setSkin(skin);
	}

	/**
	 * Reset callbacks which modify the calendar so that the calendar depends solely on the event list
	 */
	private void resetCallBack() {
		agenda.setActionCallback( new Callback<Appointment, Void>() {
			@Override
			public Void call(Appointment param) {
				logger.info(param.getSummary() + " is selected. ");
				return null;
			}
		});
		
		agenda.setEditAppointmentCallback( new Callback<Appointment, Void>() {
			@Override
			public Void call(Appointment param) {
				// Do nothing
				return null;
			}
		});
		
		agenda.setNewAppointmentCallback( new Callback<LocalDateTimeRange, Appointment>() {
			@Override
			public Appointment call(LocalDateTimeRange param) {
				// Not allowing adding new events by clicking.
				return null;
			}
		});
		
	}

	private void addToPlaceHodler() {
		SplitPane.setResizableWithParent(placeHolderPane, true);
		agenda.setId(CALENDAR_VIEW_ID);
		placeHolderPane.getChildren().add(agenda);
	}
	

	private void setBoundary() {
		AnchorPane.setTopAnchor(agenda, 0.0);
		AnchorPane.setBottomAnchor(agenda, 0.0);
		AnchorPane.setLeftAnchor(agenda, 0.0);
		AnchorPane.setRightAnchor(agenda, 0.0);
	}

	@Override
	public void setPlaceholder(AnchorPane placeholder) {
		this.placeHolderPane = placeholder;
	}
	
	/**
	 * Set data connection of calendar and the lists
	 * @param eventList
	 * @param taskList
	 */
	private void configure(List<ReadOnlyEvent> eventList, List<ReadOnlyTask> taskList) {
		setConnection(eventList, taskList);
	}
	
	private void setConnection(List<ReadOnlyEvent> eventList, List<ReadOnlyTask> taskList) {
		agenda.appointments().clear();
		agenda.selectedAppointments().clear();
		setConnectionEvent(eventList);
		setConnectionTask(taskList);
	}

	private void setConnectionEvent(List<ReadOnlyEvent> eventList) {
		eventList.forEach(event ->
			agenda.appointments().add(calHelper.convertFromEvent(event)));
	}
	
	private void setConnectionTask(List<ReadOnlyTask> taskList) {
		taskList.stream()
			.filter(task -> task.getDeadline().isPresent() && !task.getTaskStatus().booleanValue())
			.collect(Collectors.toList())
			.forEach(task -> agenda.appointments().add(calHelper.convertFromTask(task)));
	}

	/** 
	 * Refresh data shown when eventlist in model modified
	 * @param eventList
	 */
	public void refresh(List<ReadOnlyEvent> eventList, List<ReadOnlyTask> taskList) {
		logger.info("Refreshing calendar...");
		setConnection(eventList, taskList);
	}

	/**
	 * Toggle the Calendar display mode
	 * @param calendarViewMode
	 */
	public void updateCalendarMode(CalendarView calendarViewMode) {
		switch(calendarViewMode) {
		case DAY:
			agenda.setSkin(new AgendaDaySkin(agenda));
			break;
		case WEEK:
			setWeekView(DEFAULT_BEFORE, DEFAULT_AFTER);
			break;
		default:
			setWeekView(DEFAULT_BEFORE, DEFAULT_AFTER);
		}
	}
	
	/**
	 * Select an event in the calendar and show its details. 
	 * @param targetEvent
	 * @throws exception if calendar is not sync with event list. Restart needed.
	 */
	public void select(ReadOnlyEvent targetEvent) throws CalendarUnsyncException {
		// focus on the event
		LocalDateTime displayedDateTime = targetEvent.getDuration().getStartTime();
		updateCalendarShownPeriod(displayedDateTime);
		
		//highlight the event 
		Appointment targetAppoint  = agenda.appointments()
				.stream()
				.filter((Predicate<? super Agenda.Appointment>) eventInCalendar 
						-> CalendarHelper.compareWithEvent(targetEvent, eventInCalendar))
				.findAny()
				.orElseThrow(()-> new CalendarUnsyncException(CALENDAR_UNSYC_MESSAGE));
		
		agenda.selectedAppointments().add(targetAppoint);
	}

	public void select(ReadOnlyTask targetTask) throws CalendarUnsyncException {
		if(isCompleted(targetTask) || isFloatingTask(targetTask)) {
			return;
		}
		
		LocalDateTime displayedDateTime = targetTask.getDeadline().get().getTime();
		updateCalendarShownPeriod(displayedDateTime);
		
		Appointment targetAppoint = agenda.appointments().stream()
				.filter((Predicate<? super Agenda.Appointment>) taskInCalendar 
						-> CalendarHelper.compareWithTask(targetTask, taskInCalendar))
				.findAny()
				.orElseThrow(() -> new CalendarUnsyncException(CALENDAR_UNSYC_MESSAGE));
		
		agenda.selectedAppointments().add(targetAppoint);
	}
	
	/**
	 * Focus the calendar to a certain time frame
	 * @param t
	 */
	public void updateCalendarShownPeriod(LocalDateTime t) {
		agenda.setDisplayedLocalDateTime(t);
	}
	

	private boolean isFloatingTask(ReadOnlyTask targetTask) {
		return !targetTask.getDeadline().isPresent();
	}

	private boolean isCompleted(ReadOnlyTask targetTask) {
		return targetTask.getTaskStatus();
	}
	
	@Override
	public void setNode(Node node) {

	}

	/**
	 * Not use Fxml
	 * @return
	 */
	@Override
	public String getFxmlPath() {
		return "";

	}
}
