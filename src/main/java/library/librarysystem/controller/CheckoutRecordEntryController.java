package library.librarysystem.controller;

import de.vandermeer.asciitable.AsciiTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.librarysystem.business.CheckoutRecordEntry;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.CheckoutBookWindow;
import library.librarysystem.ui.CheckoutRecordTableWindow;

import java.io.IOException;

public class CheckoutRecordEntryController extends Stage {

    @FXML
    private TableView<CheckoutRecordEntry> table;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> isbn;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> title;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> copyNum;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> checkoutDate;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> dueDate;

    @FXML
    private Label memberId;
    @FXML
    private Label memberName;

    @FXML
    private Button printButton;

    public ObservableList<CheckoutRecordEntry> checkoutRecordEntries;


    public void setData(LibraryMember member) {
        memberId.setText(member.getMemberId());
        memberName.setText(member.getName());
        checkoutRecordEntries = FXCollections.observableArrayList(
                DataAccessFacade.getCheckoutRecordEntries(member.getMemberId())
        );
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getIsbn()));
        title.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getTitle()));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBookCopy().getCopyNum())));
        checkoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        table.setItems(checkoutRecordEntries);
    }


    @FXML
    public void printCheckoutRecord(ActionEvent actionEvent) {

        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("ISBN", "Title", "Copy Num", "Checkout Date", "Due Date");
        asciiTable.addRule();
        checkoutRecordEntries.forEach(checkoutRecordEntry -> {
            asciiTable.addRow(
                    checkoutRecordEntry.getBookCopy().getBook().getIsbn(),
                    checkoutRecordEntry.getBookCopy().getBook().getTitle(),
                    checkoutRecordEntry.getBookCopy().getCopyNum(),
                    checkoutRecordEntry.getCheckoutDate(),
                    checkoutRecordEntry.getDueDate()
            );
            asciiTable.addRule();
        });
        System.out.println(asciiTable.render());
    }
}
