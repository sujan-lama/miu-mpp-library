package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.AddLibraryMemberController;
import library.librarysystem.dataaccess.Auth;

import java.io.IOException;

public class AddLibraryMemberWindow extends Stage implements LibWindow {

    public static AddLibraryMemberWindow INSTANCE = new AddLibraryMemberWindow();

    private boolean isInitialized = false;


    private LibraryMember libraryMember = null;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Add Library Member");
        fxmlLoader = new FXMLLoader(getClass().getResource("addLibraryMember.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 309, 725);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(LibraryMember member) {
        this.libraryMember = member;
        ((AddLibraryMemberController) fxmlLoader.getController()).setData(libraryMember);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void addoreditMemberSuccess(LibraryMember member) {
        hide();
        HomeWindow.INSTANCE.updateLibraryMember(member);

    }
}
