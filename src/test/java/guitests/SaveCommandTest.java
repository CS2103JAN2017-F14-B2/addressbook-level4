package guitests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.task.logic.commands.SaveCommand.MESSAGE_SUCCESS;

import java.io.File;

import static seedu.task.logic.commands.SaveCommand.MESSAGE_FAILURE_DIRECTORY;

import org.junit.Test;

import seedu.task.testutil.TestUtil;

public class SaveCommandTest extends TaskManagerGuiTest {

	@Test
	public void save() {
		//saves to directory
		commandBox.runCommand("save data");
		assertResultMessage(String.format(MESSAGE_FAILURE_DIRECTORY, "data"));
		//saves to new file
		assertNewFileSaved(TestUtil.getFilePathInSandboxFolder("saveTestDummy.xml"));
		assertNewFileSaved("./src/test/data/saveTestDummy2.xml");
	}
	
	/**
	 * Saves the file and assert that it exists then deletes the file
	 * @param location referes to the path of the file
	 */
	public void assertNewFileSaved(String location) {
		File target = new File(location);
		assertFalse(target.exists());
		commandBox.runCommand("save " + location);
		assertTrue(target.exists());
		assertTrue(target.delete());
	}
}