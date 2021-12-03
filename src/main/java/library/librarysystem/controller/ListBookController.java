package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.ui.ListBookWindow;

import java.util.ArrayList;
import java.util.List;

public class ListBookController extends Stage {

    @FXML
    public ListView<String> listView;

    public void setData(List<Book> books) {
        List<String> newBooks = new ArrayList<>();
        int i = 1;
        for (Book book : books) {
            newBooks.add(i + ". " + book.getTitle());
            i++;
        }
        listView.getItems().addAll(newBooks);

        listView.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        ListBookWindow.INSTANCE.goToDetailOfBook(books.get(listView.getSelectionModel().getSelectedIndices().get(0)));
                    }
                }
        );
    }

    @FXML
    public void goBack() {
        ListBookWindow.INSTANCE.goBack();
    }

    public void showDetail(String id) {

    }
}
