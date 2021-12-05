package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.controller.ListAuthorController;

import java.io.IOException;
import java.util.List;

public class ListAuthorWindow extends Stage implements LibWindow {
    public static ListAuthorWindow INSTANCE = new ListAuthorWindow();

    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("listAuthor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(List<Author> authorList) {
        ListAuthorController controller = fxmlLoader.getController();
        controller.setData(authorList);
        show();
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }
}
