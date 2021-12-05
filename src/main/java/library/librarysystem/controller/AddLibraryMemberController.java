package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.Address;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.ui.AddLibraryMemberWindow;
import library.librarysystem.utils.TextFieldUtils;

import java.util.List;

public class AddLibraryMemberController extends Stage {

    @FXML
    private Label label;
    @FXML
    private TextField id;
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
    private Button submit;

    Alert alert = new Alert(Alert.AlertType.NONE);

    private LibraryMember member;

    @FXML
    public void goBack() {
        AddLibraryMemberWindow.INSTANCE.goBack();
    }


    public void addLibraryMember(LibraryMember member, boolean update) {

        ControllerInterface controller = new SystemController();
        if (!update)
            controller.saveMember(member);
        else
            controller.editMember(member);
        AddLibraryMemberWindow.INSTANCE.addoreditMemberSuccess(member);

    }

    @FXML
    public void submit(ActionEvent event) {
        String textId = id.getText().trim();
        String textFirstName = firstName.getText().trim();
        String textLastName = lastName.getText().trim();
        String textPhoneNumber = phoneNumber.getText().trim();
        String textStreet = street.getText().trim();
        String textCity = city.getText().trim();
        String textState = state.getText().trim();
        String textZip = zip.getText().trim();
        Address address = new Address(textStreet, textCity, textState, textZip);
        LibraryMember newMember = new LibraryMember(textId, textFirstName, textLastName, textPhoneNumber, address);
        boolean validate = validate(newMember);
        if (!validate) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Some fields are missing. Please check and try again.");
            alert.show();
            return;
        }

        if (member != null) {
            addLibraryMember(newMember, true);
            return;
        }
        boolean checkIdDifferent = checkIdDifferent(textId);
        if (!checkIdDifferent) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Cannot have same id");
            alert.show();
            return;
        }
        addLibraryMember(newMember, false);
        clear();
        alert.close();
    }

    private boolean checkIdDifferent(String textId) {
        ControllerInterface ci = new SystemController();
        List<LibraryMember> libraryMembers = ci.allMembers();
        libraryMembers = libraryMembers.stream().filter(member -> member.getMemberId().equals(textId)).toList();
        return libraryMembers.size() == 0;
    }

    private boolean validate(LibraryMember newMember) {
        return !newMember.isEmpty();
    }

    private void clear() {
        id.setText("");
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        street.setText("");
        city.setText("");
        state.setText("");
        zip.setText("");
    }


    public void setData(LibraryMember member) {
        this.member = member;
        id.setTextFormatter(TextFieldUtils.integerTextFormatter());
        zip.setTextFormatter(TextFieldUtils.integerTextFormatter());
        if (member == null) {
            label.setText("Add Library Member");
            submit.setText("Submit");
            id.setDisable(false);
            return;
        }
        label.setText("Update Library Member");
        submit.setText("Update");

        id.setText(member.getMemberId());
        id.setDisable(true);
        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        phoneNumber.setText(member.getTelephone());
        street.setText(member.getAddress().getStreet());
        city.setText(member.getAddress().getCity());
        state.setText(member.getAddress().getState());
        zip.setText(member.getAddress().getZip());
    }
}
