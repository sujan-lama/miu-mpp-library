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

    private boolean fromAdd = false;
    private FXMLLoader fxmlLoader;


    @Override
    public void init() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("libraryMemberDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setLibraryMember(LibraryMember libraryMember, boolean fromAdd) {
        this.fromAdd = fromAdd;
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
            if (!fromAdd) {
                ListLibraryMemberWindow.INSTANCE.init();
            } else {
                AddLibraryMemberWindow.INSTANCE.init();
                AddLibraryMemberWindow.INSTANCE.setDataAndShow(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
