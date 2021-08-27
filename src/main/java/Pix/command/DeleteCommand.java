package Pix.command;

import Pix.exception.PixException;
import Pix.exception.PixIOException;
import Pix.storage.Storage;
import Pix.task.TaskList;
import Pix.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for the delete command.
     *
     * @param taskNumber Task number to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Triggers the done command which completes a task in the task list.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) throws PixException {
        try {
            storage.writeToFile(taskList.getTaskList());
        } catch (IOException | PixIOException e) {
            //The format should be set and there shouldn't be any I/O error.
        }

        return taskList.deleteTask(taskNumber);
    }
}