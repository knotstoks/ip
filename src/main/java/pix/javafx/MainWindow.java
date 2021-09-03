package pix.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pix.Pix;
import pix.command.Command;
import pix.exception.PixException;
import pix.parser.Parser;
import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Pix pix;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pix.png"));

    /**
     * Initializes the Main Window.
     */
    @FXML
    public void initialize() {
        ui = new Ui();
        try {
            storage = new Storage("./data/Pix.txt");
            taskList = new TaskList(storage.load());
        } catch (PixException e) {
            taskList = new TaskList();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogueBox.getPixDialog(ui.displayWelcome(), dukeImage));
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws PixException {
        String input = userInput.getText();
        String response = "";
        try {
            Command command = Parser.parse(input);
            response += command.trigger(storage, taskList, ui);
        } catch (PixException e) {
            response += e.getMessage();
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogueBox.getUserDialog(input, userImage),
                    DialogueBox.getPixDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

    /**
     * Closes the application when the 'X' button is clicked.
     */
    public void closeWindowButtonClicked() {
        Platform.exit();
    }
}
















