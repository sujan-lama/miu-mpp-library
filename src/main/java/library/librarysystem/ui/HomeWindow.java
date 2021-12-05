package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.HomeController;

import java.io.IOException;

public class HomeWindow extends Stage implements LibWindow {
    public static HomeWindow INSTANCE = new HomeWindow();
    private boolean isInitialized = false;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        setTitleByAuth();
        fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 712, 472);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        HomeController controller = fxmlLoader.getController();
        controller.setVisibilityByAuth(SystemController.currentAuth);
        controller.setLibraryMember();
        controller.setBooks();
        show();
    }

    private void setTitleByAuth() {
        switch (SystemController.currentAuth) {
            case BOTH -> super.setTitle("Super Admin Home Page");
            case ADMIN -> super.setTitle("Admin Home Page");
            case LIBRARIAN -> super.setTitle("Librarian Home Page");
        }
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
        try {
            LoginWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
        LoginWindow.INSTANCE.show();
    }

    public void updateBook(Book book) {
        HomeController controller = fxmlLoader.getController();
        controller.updateBookTable(book);
    }

    public void updateLibraryMember(LibraryMember member) {
        HomeController controller = fxmlLoader.getController();
        controller.updateLibraryMember(member);
    }
}
