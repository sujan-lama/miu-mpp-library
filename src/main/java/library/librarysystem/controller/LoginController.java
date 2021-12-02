package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LoginException;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.LoginWindow;
import library.librarysystem.ui.Start;

public class LoginController extends Stage {

    @FXML
    private TextField userTextField;

    @FXML
    private Label messageBar;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    public void loginValid(ActionEvent event) {

        messageBar.setText("");
        try {
            ControllerInterface c = new SystemController();
            c.login(userTextField.getText().trim(), passwordTextField.getText().trim());
            messageBar.setTextFill(Start.Colors.green);
            messageBar.setText("Login successful");
            LoginWindow.INSTANCE.loginSuccessful(SystemController.currentAuth);

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
