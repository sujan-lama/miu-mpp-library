package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.AdminController;

import java.io.IOException;

public class AdminWindow extends Stage implements LibWindow {

    public static final AdminWindow INSTANCE = new AdminWindow();

    private boolean isInitialized = false;

    private boolean fromSuperAdmin = false;

    @Override
    public void init() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(AdminWindow.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 540, 490);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        if (fromSuperAdmin)
            ((AdminController) fxmlLoader.getController()).setFromSuperAdmin();
        show();
    }

    public void setData(boolean fromSuperAdmin) {
        this.fromSuperAdmin = fromSuperAdmin;
    }

    public void gotoAddLibraryMember() {
        hide();
        try {
            AddLibraryMemberWindow.INSTANCE.init();
            AddLibraryMemberWindow.INSTANCE.setDataAndShow(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoAddBooks() {
        hide();
        try {
            AddBookWindow.INSTANCE.init();
            AddBookWindow.INSTANCE.setDataAndShow(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void gotoViewLibraryMember() {
        hide();
        try {
            ListLibraryMemberWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoViewBooks() {
        hide();
        try {
            ListBookWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoOverdueBooks() {
        hide();
        try {
            OverdueWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoSearchBooks() {
        hide();
        try {
            SearchBooksWindow.INSTANCE.init();
            SearchBooksWindow.INSTANCE.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {

        if (fromSuperAdmin) {
            hide();
            try {
                SuperAdminWindow.INSTANCE.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            ControllerInterface controller = new SystemController();
            controller.logout();
            LoginWindow.INSTANCE.init();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        hide();
        LoginWindow.INSTANCE.show();
    }
}
