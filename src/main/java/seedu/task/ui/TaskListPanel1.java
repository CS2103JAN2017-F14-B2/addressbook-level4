package seedu.task.ui;

import java.util.logging.Logger;

import org.ocpsoft.prettytime.shade.edu.emory.mathcs.backport.java.util.Collections;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.task.commons.util.FxViewUtil;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.TaskComparator;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel1 extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(TaskListPanel1.class);
    private static final String FXML = "TaskListPanel1.fxml";

    @FXML
    private ListView<ReadOnlyTask> taskListView;

    public TaskListPanel1(AnchorPane taskListPlaceholder, ObservableList<ReadOnlyTask> taskList) {
        super(FXML);
        setConnections(taskList);
        addToPlaceholder(taskListPlaceholder);
    }

    private void setConnections(ObservableList<ReadOnlyTask> taskList) {
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder(AnchorPane placeHolderPane) {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        FxViewUtil.applyAnchorBoundaryParameters(getRoot(), 0.0, 0.0, 0.0, 0.0);
        placeHolderPane.getChildren().add(getRoot());
    }

    private void setEventHandlerForSelectionChangeEvent() {
        taskListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in task list panel changed to : '" + newValue + "'");
                        raise(new TaskPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            taskListView.scrollTo(index);
            taskListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class TaskListViewCell extends ListCell<ReadOnlyTask> {

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
           super.updateItem(task, empty);
            
            if (empty || task == null || task.isDone()==true) {
               System.out.println("yes");
              setGraphic(null);
              setText(null);
             setDisable(false);
              setTextFill(Color.BLACK);
              setStyle(""); 
             
            }else{
                System.out.println("out");
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
