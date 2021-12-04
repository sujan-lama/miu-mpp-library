package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.CheckInBookController;

import java.io.IOException;

public class CheckInBookWindow extends Stage implements LibWindow {
    public static CheckInBookWindow INSTANCE = new CheckInBookWindow();
    private boolean isInitialized = false;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("checkinBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 242);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(String memberId, String name) {
        ((CheckInBookController) fxmlLoader.getController()).setData(memberId, name);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }
}
