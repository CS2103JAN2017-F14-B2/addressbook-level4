package seedu.task.logic.commands;

import java.io.IOException;
import java.util.Optional;

import seedu.task.commons.core.History;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.ReadOnlyTaskManager;
import seedu.task.model.task.Task;

/**
 * Redo last undo.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD_1 = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful!";
    public static final String MESSAGE_FAIL_NOT_FOUND = "Unable to redo. Can only redo after undo.";
    public static final String MESSAGE_FAIL = "Nothing to redo. Already at latest state.";
    public static final String MESSAGE_USAGE = COMMAND_WORD_1
            + ": Redo the most recent undo.\n"
            + "Example: " + COMMAND_WORD_1;
    private History history = History.getInstance();

    @Override
    public CommandResult execute() {
        assert model != null;
        assert storage != null;

        int redoCount = history.getRedoCount();

        if (redoCount <= 0) {
            return new CommandResult(MESSAGE_FAIL);
        }

        Optional<ReadOnlyTaskManager> taskManagerOptional;
        ReadOnlyTaskManager backupData;

        try {
            taskManagerOptional = storage.readTaskManager(history.getRedoFilePath());
            if (!taskManagerOptional.isPresent()) {
                return new CommandResult(MESSAGE_FAIL_NOT_FOUND);
            }
            backupData = taskManagerOptional.get();
            model.undoData(backupData);
        } catch (DataConversionException e) {
            return new CommandResult(MESSAGE_FAIL_NOT_FOUND);
        } catch (IOException e) {
            return new CommandResult(MESSAGE_FAIL_NOT_FOUND);
        } catch (IllegalValueException ive) {
            return new CommandResult(Task.MESSAGE_TASK_CONSTRAINTS);
        }
        history.handleRedo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
