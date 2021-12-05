package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.librarysystem.business.Author;

import java.util.ArrayList;
import java.util.List;

public class ListAuthorController extends Stage {

    @FXML
    public AnchorPane details;
    @FXML
    public ListView<String> authorListView;
    @FXML
    public Label name;
    @FXML
    public Label phone;
    @FXML
    public Label address;
    @FXML
    public Label credentials;
    @FXML
    public Label street;
    @FXML
    public Label city;
    @FXML
    public Label state;
    @FXML
    public Label zip;

    public void setData(List<Author> authorList) {
        List<String> authorsListString = new ArrayList<>();
        details.setVisible(false);
        for (Author author : authorList) {
            authorsListString.add(author.getFirstName() + " " + author.getLastName());
        }
        authorListView.getItems().addAll(authorsListString);

        authorListView.setOnMousePressed(e -> {
            int index = authorListView.getSelectionModel().getSelectedIndex();
            if (e.getButton().equals(MouseButton.PRIMARY) && index != -1) {
                details.setVisible(true);
                setText(authorList.get(index));
            }
        });
    }

    private void setText(Author author) {
        name.setText(author.getName());
        phone.setText(author.getTelephone());
        credentials.setText(author.isHasCredentials() ? "Expert" : "Not expert");
        street.setText(author.getAddress().getStreet());
        city.setText(author.getAddress().getCity());
        state.setText(author.getAddress().getState());
        zip.setText(author.getAddress().getZip());
    }
}
