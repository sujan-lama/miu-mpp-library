package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;

import java.io.IOException;

public class SuperAdminWindow extends Stage implements LibWindow {
    public static SuperAdminWindow INSTANCE = new SuperAdminWindow();
    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Super Admin Home Page");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("superadmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 300);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void logout() {
        ControllerInterface ci = new SystemController();
        ci.logout();
        hide();
        try {
            LoginWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginWindow.INSTANCE.show();
    }
}
