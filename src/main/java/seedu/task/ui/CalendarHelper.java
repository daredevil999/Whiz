package seedu.task.ui;

import seedu.task.model.item.ReadOnlyEvent;
import seedu.task.model.item.ReadOnlyStock;
import java.util.HashMap;
import java.util.Map;

import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplBase;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;

//@@author A0144702N
public class CalendarHelper extends AppointmentImplBase implements Appointment {
	private static final String EVENT_GROUP = "group11";
	private static final long DEFAULT_DURATION = 1;
	private static final String TASK_GROUP = "group10";
	private static Map<String, AppointmentGroup> groupMap;
	private static CalendarHelper instance;
	
	
	private CalendarHelper() {
		setGroups();
	}
	
	private static void setGroups() {
		groupMap = new HashMap<>();
		for (AppointmentGroup group : new Agenda().appointmentGroups()) {
			groupMap.put(group.getDescription(), group);
		}
	}
	
	public Appointment convertFromEvent(ReadOnlyEvent event) {
		Appointment item = new AppointmentImplLocal();
		item.setSummary(event.getEvent().fullName);
		item.setStartLocalDateTime(event.getDuration().getStartTime());
		item.setEndLocalDateTime(event.getDuration().getEndTime());
		item.setDescription(event.getDescriptionValue());
		item.setAppointmentGroup(groupMap.get(EVENT_GROUP));
		
		return item;
	}
	

	public Appointment convertFromTask(ReadOnlyStock task) {
		Appointment item = new AppointmentImplLocal();
		item.setSummary(task.getTask().fullName);
		item.setStartLocalDateTime(task.getPurchaseDate().get().getTime());
		item.setEndLocalDateTime(item.getStartLocalDateTime().plusHours(DEFAULT_DURATION));
		item.setDescription(task.getDescriptionValue());
		item.setAppointmentGroup(groupMap.get(TASK_GROUP));
		return item;
	}

	public static CalendarHelper getInstance() {
		if (instance == null) {
			instance = new CalendarHelper();
		}
		return instance;
	}

	/**
	 * Compare the equality of a task with an item in the calendar.
	 * @param targetTask
	 * @param taskInCalendar
	 * @return
	 */
	public static boolean compareWithTask(ReadOnlyStock targetTask, Appointment taskInCalendar) {
		assert targetTask.getPurchaseDate().isPresent();
		return taskInCalendar.getSummary().equals(targetTask.getTask().getNameValue())
				&& taskInCalendar.getStartLocalDateTime().equals(targetTask.getPurchaseDate().get().getTime());
	}

	public static boolean compareWithEvent(ReadOnlyEvent targetEvent, Appointment eventInCalendar) {
		return eventInCalendar.getSummary().equals(targetEvent.getEvent().getNameValue())
				&& eventInCalendar.getStartLocalDateTime().equals(targetEvent.getDuration().getStartTime())
				&& eventInCalendar.getEndLocalDateTime().equals(targetEvent.getDuration().getEndTime());
	}
	
	public boolean isTask(Appointment appointment) {
		AppointmentGroup group =  appointment.getAppointmentGroup();
		return group.getStyleClass().equals(TASK_GROUP);
		
	}

	public boolean isEvent(Appointment appointment) {
		return !isTask(appointment);
		
	}
}
