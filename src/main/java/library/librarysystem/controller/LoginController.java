package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.*;
import library.librarysystem.ui.Start;

public class LoginController extends Stage {

    @FXML
    private TextField userTextField;

    @FXML
    private Label messageBar;

    @FXML
    private PasswordField passwordTextField;


    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void loginValid(ActionEvent event) {

        messageBar.setText("");
        try {
            ControllerInterface c = new SystemController();
            c.login(userTextField.getText().trim(), passwordTextField.getText().trim());
            Start.hideAllWindows();
            messageBar.setTextFill(Start.Colors.green);
            messageBar.setText("Login successful");
        } catch (LoginException ex) {
            messageBar.setTextFill(Start.Colors.red);
            messageBar.setText("Error! " + ex.getMessage());
        }
    }

    @FXML
    public void onBackPressed() {
        Start.hideAllWindows();
        Start.primStage().show();
    }
}
