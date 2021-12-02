package library.librarysystem.ui;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LoginException;
import library.librarysystem.business.SystemController;

import java.io.IOException;


public class LoginWindow extends Stage implements LibWindow {
    public static final LoginWindow INSTANCE = new LoginWindow();

    private boolean isInitialized = false;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private Text messageBar = new Text();

    public void clear() {
        messageBar.setText("");
    }

    /* This class is a singleton */
    private LoginWindow() {
    }

    public void init() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);

    }


}
