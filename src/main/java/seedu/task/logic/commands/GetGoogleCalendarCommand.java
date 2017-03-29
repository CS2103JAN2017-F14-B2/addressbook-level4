package seedu.task.logic.commands;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import seedu.task.commons.core.GoogleCalendar;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.Date;
import seedu.task.model.task.Location;
import seedu.task.model.task.Name;
import seedu.task.model.task.Remark;
import seedu.task.model.task.Task;

/**
 * Undo last task.
 */
public class GetGoogleCalendarCommand extends Command {

    private static final Logger logger = LogsCenter.getLogger(LogsCenter.class);
    public static final String COMMAND_WORD_1 = "getgoogle";
    public static final String COMMAND_WORD_2 = "ggc";
    public static final String MESSAGE_SUCCESS = "Upcoming events obtained from Google successfully!\n"
            + "Note that duplicate events are ignored.";
    public static final String MESSAGE_FAIL = "Unable to retrieve from Google.";
    public static final String MESSAGE_USAGE = COMMAND_WORD_1
            + ": Gets your events from your Google Calendar and add them to KIT."
            + " Please note that this will only get upcoming events.\n"
            + "Example: " + COMMAND_WORD_2;

    @Override
    public CommandResult execute() {

        try {
            com.google.api.services.calendar.Calendar service = GoogleCalendar.getCalendarService();
            Events events = service.events().list("primary").setSingleEvents(true).execute();
            List<Event> items = events.getItems();
            if (items.size() == 0) {
                return new CommandResult("No events found");
            } else {
                logger.info("Events retrieved from Google Calendar. Attempting to add.");
                for (Event event : items) {

                    try {
                        Name name = new Name(event.getSummary());
                        Date startDate = new Date(event.getStart());
                        Date endDate = new Date(event.getEnd());
                        Remark remark = new Remark(event.getDescription());
                        Location location = new Location(event.getLocation());
                        final Set<Tag> tagSet = new HashSet<>(); //No tags
                        Task toAdd = new Task(name, startDate, endDate, remark, location,
                                new UniqueTagList(tagSet), false);
                        assert model != null;
                        model.addTask(toAdd);
                        logger.info("Event added.");
                    } catch (IllegalValueException ive) {
                        logger.info(ive.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            return new CommandResult(MESSAGE_FAIL);
        }

        model.sortTaskList();
        model.updateFilteredListToShowAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}