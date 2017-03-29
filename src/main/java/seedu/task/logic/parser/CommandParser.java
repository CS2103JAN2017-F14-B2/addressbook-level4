package seedu.task.logic.parser;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.logic.commands.Command;
/**
 * An abstract CommandParser super class
 * The implementation of the parse method is up to its subclasses
 * @author Xu
 *
 */
public abstract class CommandParser {

    public abstract Command parse(String args);

}
