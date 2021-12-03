package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.AllMemberController;

import java.io.IOException;

public class AllMembersWindow extends Stage implements LibWindow {
    public static final AllMembersWindow INSTANCE = new AllMembersWindow();

    private boolean isInitialized = false;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    /* This class is a singleton */
    private AllMembersWindow() {
    }


    private FXMLLoader loader;

    public void setData(String data) {
        ((AllMemberController) loader.getController()).fillData(data);
    }

    public void init() throws IOException {
        super.setTitle("All Members");
        loader = new FXMLLoader(AllBooksWindow.class.getResource("allmember.fxml"));
        Scene scene = new Scene(loader.load(), 480, 450);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized(true);
    }
}
