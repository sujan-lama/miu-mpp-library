package library.librarysystem.ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.librarysystem.business.SystemController;
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);

    }

    public void loginSuccessful(Auth currentAuth) {
        Start.hideAllWindows();
        try {
            switch (currentAuth) {
                case ADMIN -> AdminWindow.INSTANCE.init();
                case LIBRARIAN -> System.out.println("librarian");
                case BOTH -> System.out.println("both");
            }
            AdminWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
