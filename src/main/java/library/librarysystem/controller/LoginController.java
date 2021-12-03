package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import library.librarysystem.business.*;
import library.librarysystem.ui.Start;

import java.io.IOException;

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
            String fxml = "";
            switch (SystemController.currentAuth) {
                case ADMIN -> fxml = "admin.fxml";
                case LIBRARIAN -> fxml = "librarian.fxml";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(fxml));
            try {
                Start.hideAllWindows();
                Scene scene = new Scene(fxmlLoader.load(), 480, 360);
                scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
                setScene(scene);
                show();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
