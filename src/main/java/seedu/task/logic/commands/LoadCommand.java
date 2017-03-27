package seedu.task.logic.commands;

import java.io.File;

import seedu.task.commons.util.FileUtil;
//@@author A0142939W
/**
 * Saves task manager in a different directory.
 */
public class LoadCommand extends Command {

	public static final String COMMAND_WORD = "load";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Loads the task manager from a different directory. "
			+ "Parameters: PATHNAME \n"
			+ "Example: " + COMMAND_WORD
			+ " ";

	public static final String MESSAGE_SUCCESS = "File loaded from: %1$s";
	public static final String MESSAGE_FAILURE_DIRECTORY = "The path %1$s leads to a directory";
	public static final String MESSAGE_NOT_FOUND = "The file %1$s cannot be found";
	public static final String MESSAGE_WRONG_FORMAT = "The file %1$s is not in a compatible xml format";
	private String pathName;

	/**
	 * Creates a Save command
	 */
	public LoadCommand(String pathName) {
		this.pathName = pathName;
	}

	@Override
	public CommandResult execute() {
		File file = new File(pathName);

		if (FileUtil.isFileDirectory(file)) {
			return new CommandResult(String.format(MESSAGE_FAILURE_DIRECTORY, pathName));
		}

		if (!FileUtil.isFileExists(file)) {
			return new CommandResult(String.format(MESSAGE_NOT_FOUND, pathName));
		}

		if (!FileUtil.isFileFormatCorrect(file)) {
			return new CommandResult(String.format(MESSAGE_WRONG_FORMAT, pathName));
		}

		model.loadFromLocation(pathName);
		return new CommandResult(String.format(MESSAGE_SUCCESS, pathName));
	}

}
