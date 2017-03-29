package seedu.task.logic.commands;


//@@author A0142939W
/**
 * Saves task manager in a different directory.
 */
public class ThemeChangeCommand extends Command {

    public static final String COMMAND_WORD_1 = "changetheme";

    public static final String MESSAGE_USAGE = COMMAND_WORD_1 + ": Changes the theme of the task manager. "
            + "Parameters: Theme Name \n"
            + "Example: " + COMMAND_WORD_1
            + " Dark";
    
    public static final String MESSAGE_SUCCESS = "Theme is successfully changed to %1$s. Restart KIT to view the changes.";

    private String themeName;

    /**
     * Creates a Save command
     */
    public ThemeChangeCommand(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public CommandResult execute() {
        storage.setThemeTo(themeName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, themeName));
    }


}
