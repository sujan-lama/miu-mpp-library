package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.controller.LibraryMemberDetailController;

import java.io.IOException;

public class LibraryMemberDetailWindow extends Stage implements LibWindow {


    public static final LibraryMemberDetailWindow INSTANCE = new LibraryMemberDetailWindow();
    private boolean isInitialized = false;

    private FXMLLoader fxmlLoader;


    @Override
    public void init() throws IOException {
        super.setTitle("Librarian Member Detail");
        fxmlLoader = new FXMLLoader(getClass().getResource("libraryMemberDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        ((LibraryMemberDetailController) fxmlLoader.getController()).setData(libraryMember);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void goBack() {
        hide();
        try {
            ListLibraryMemberWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
