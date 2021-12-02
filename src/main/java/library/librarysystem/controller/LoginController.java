package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LoginException;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.Start;

public class LoginController {

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
