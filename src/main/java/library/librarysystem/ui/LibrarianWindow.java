package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.LibrarianController;

import java.io.IOException;

public class LibrarianWindow extends Stage implements LibWindow {
    public static LibrarianWindow INSTANCE = new LibrarianWindow();
    public boolean fromSuperAdmin = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Librarian Home Page");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("librarian.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        if (fromSuperAdmin)
            ((LibrarianController) fxmlLoader.getController()).setFromSuperAdmin();
        setScene(scene);
        show();
    }

    public void setFromSuperAdmin(boolean fromSuperAdmin) {
        this.fromSuperAdmin = fromSuperAdmin;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void logOut() {
        hide();
        if (fromSuperAdmin) {
            try {
                SuperAdminWindow.INSTANCE.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            ControllerInterface ci = new SystemController();
            ci.logout();
            LoginWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginWindow.INSTANCE.show();
    }
}
