package seedu.task.model;

import java.util.Set;

import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.model.LoadNewFileSuccessEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data.
     * @throws IllegalValueException */
    void resetData(ReadOnlyTaskManager newData) throws IllegalValueException;

    /** Undo last command.
     * @throws IllegalValueException */
    void undoData(ReadOnlyTaskManager newData) throws IllegalValueException;

    /** Returns the TaskManager. */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given task. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Updates the task to done. */
    void isDoneTask(int index, ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Updates the task from done to undone */
    void unDoneTask(int index, ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given Task. */
    void addTask(Task task) throws UniqueTaskList.DuplicateTaskException;

    /**
     * Updates the task located at {@code filteredTaskListIndex} with {@code editedTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IllegalValueException
     * @throws IndexOutOfBoundsException if {@code filteredTaskListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException, IllegalValueException;

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered task list to filter by the given keywords */
    void updateFilteredTaskList(Set<String> keywords);

    /** Updates the filter of the filtered task list to filter by any or exactly all of the given keywords */
    void updateFilteredTaskList(Set<String> keywords, boolean isExact);

    /** Updates the filter of the filtered task list to filter by the given keyword of tags */
    void updateFilteredTaskList(String keyword);

    /** Updates the filer of the filtered task list to filter by done or undone task */
    void updateFilteredTaskList(boolean value);

    /** Changes the file path for data to be saved in */
    void changeFilePath(String pathName);

    /** Changes the load path for data to be loaded from*/
    void loadFromLocation(String loadPath);

    /** Loads the file from the path to be loaded from*/
    void handleLoadNewFileSuccessEvent(LoadNewFileSuccessEvent event);

    /** Sorts the task list */
    void sortTaskList();

}
