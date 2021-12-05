package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.controller.OverdueTableWindowController;

import java.io.IOException;

public class OverdueTableWindow extends Stage implements LibWindow {

    public static OverdueTableWindow INSTANCE = new OverdueTableWindow();

    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Overdue Table");
        fxmlLoader = new FXMLLoader(getClass().getResource("overdueTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 566, 418);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(Book book) {
        OverdueTableWindowController controller = fxmlLoader.getController();
        controller.setData(book);
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
