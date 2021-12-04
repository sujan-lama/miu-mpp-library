package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.ListLibraryMemberController;

import java.io.IOException;
import java.util.List;

public class ListLibraryMemberWindow extends Stage implements LibWindow {

    public static ListLibraryMemberWindow INSTANCE = new ListLibraryMemberWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Members List");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewMember.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        ControllerInterface ci = new SystemController();
        List<LibraryMember> libraryMembers = ci.allMembers();
        ((ListLibraryMemberController) fxmlLoader.getController()).setData(libraryMembers);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void goToDetailOfLibraryMember(LibraryMember member) {
        hide();
        try {
            LibraryMemberDetailWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LibraryMemberDetailWindow.INSTANCE.setLibraryMember(member);
    }

    public void goBack() {
        hide();
        AdminWindow.INSTANCE.show();
    }
}
