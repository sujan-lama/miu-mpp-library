package library.librarysystem.ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.librarysystem.dataaccess.Auth;

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
        super.setTitle("Login");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 463, 450);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);

    }

    public void loginSuccessful(Auth currentAuth) {
        Start.hideAllWindows();
        try {
            HomeWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
