package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.AddLibraryMemberController;

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

    public void gotoLibraryMember(LibraryMember member) {
        hide();
        try {
            LibraryMemberDetailWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LibraryMemberDetailWindow.INSTANCE.setLibraryMember(member);
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
            if (libraryMember == null) {
                AdminWindow.INSTANCE.init();
            } else {
                ListLibraryMemberWindow.INSTANCE.init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addoreditMemberSuccess(LibraryMember member) {
        hide();
        switch(SystemController.currentAuth){
            case BOTH -> {
                HomeWindow.INSTANCE.updateLibraryMember(member);
            }
        }
    }
}
