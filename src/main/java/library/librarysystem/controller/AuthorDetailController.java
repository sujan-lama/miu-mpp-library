package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.ui.AddAuthorWindow;
import library.librarysystem.ui.AuthorDetailWindow;

import java.io.IOException;
import java.util.Optional;

public class AuthorDetailController extends Stage {


    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label credentials;

    @FXML
    private Label address;

    private Author author;

    @FXML
    private MenuBar options;

    public void setData(Author author, boolean fromAdd) {
        this.author = author;
        name.setText(author.getFirstName() + " " + author.getLastName());
        phone.setText(author.getTelephone());
        options.setVisible(fromAdd);
        credentials.setText(author.isHasCredentials() ? "Author is expert in that area and have qualification" : "Author is not expert in that area and does not have qualification");
        address.setText(author.getAddress().getStreet() + ", " + author.getAddress().getCity() + ", " + author.getAddress().getState() + ", " + author.getAddress().getZip());
    }

    @FXML
    public void deleteAuthorButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setHeaderText("Delete " + author.getFirstName() + " " + author.getLastName().trim() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            AuthorDetailWindow.INSTANCE.deleteAuthor(author);
        }

    }

    @FXML
    public void editAuthor() {
        AuthorDetailWindow.INSTANCE.hide();
        try {
            AddAuthorWindow.INSTANCE.init();
            AddAuthorWindow.INSTANCE.setDataAndShow(author);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
