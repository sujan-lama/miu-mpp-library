package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import library.librarysystem.business.Address;
import library.librarysystem.business.Author;
import library.librarysystem.ui.AddAuthorWindow;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.utils.TextFieldUtils;

public class AddAuthorController extends Stage {

    @FXML
    private Label label;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField bio;

    @FXML
    private CheckBox credentials;

    @FXML
    private Button submit;

    Alert alert = new Alert(Alert.AlertType.NONE);

    private Author author;


    public void addAuthor(Author author, Author previousAuthor) {
        AddBookWindow.INSTANCE.addAuthor(author, previousAuthor);
        AddAuthorWindow.INSTANCE.hide();
    }

    @FXML
    public void submit(ActionEvent event) {
        String textFirstName = firstName.getText().trim();
        String textLastName = lastName.getText().trim();
        String textPhoneNumber = phoneNumber.getText().trim();
        String textStreet = street.getText().trim();
        String textCity = city.getText().trim();
        String textState = state.getText().trim();
        String textZip = zip.getText().trim();
        String textBio = bio.getText().trim();
        boolean credentialsValue = credentials.isSelected();
        Address address = new Address(textStreet, textCity, textState, textZip);
        Author newAuthor = new Author(textFirstName, textLastName, textPhoneNumber, address, textBio, credentialsValue);
        boolean validate = validate(newAuthor);
        if (!validate) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Some fields are missing. Please check and try again.");
            alert.show();
            return;
        }

        if (author != null) {
            addAuthor(newAuthor, author);
            return;
        }

        addAuthor(newAuthor, null);
        clear();
        alert.close();
    }


    private boolean validate(Author newAuthor) {
        return !newAuthor.isEmpty();
    }

    private void clear() {
        bio.setText("");
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        street.setText("");
        city.setText("");
        state.setText("");
        zip.setText("");
    }


    public void setData(Author author) {
        this.author = author;
        zip.setTextFormatter(TextFieldUtils.integerTextFormatter());
        if (author == null) {
            label.setText("Add  Author");
            submit.setText("Submit");
            return;
        }
        label.setText("Update  Author");
        submit.setText("Update");
        credentials.setSelected(author.isHasCredentials());
        bio.setText(author.getBio());
        firstName.setText(author.getFirstName());
        lastName.setText(author.getLastName());
        phoneNumber.setText(author.getTelephone());
        street.setText(author.getAddress().getStreet());
        city.setText(author.getAddress().getCity());
        state.setText(author.getAddress().getState());
        zip.setText(author.getAddress().getZip());
    }
}
