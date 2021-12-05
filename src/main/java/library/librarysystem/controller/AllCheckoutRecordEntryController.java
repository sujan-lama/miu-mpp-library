package library.librarysystem.controller;

import de.vandermeer.asciitable.AsciiTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.librarysystem.business.CheckoutRecordEntry;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.AllCheckoutRecordTableWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllCheckoutRecordEntryController extends Stage {

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
    private TableColumn<CheckoutRecordEntry, String> memberId;
    @FXML
    private TableColumn<CheckoutRecordEntry, String> memberName;
    @FXML
    private Button printButton;

    public ObservableList<CheckoutRecordEntry> checkoutRecordEntries;


    public void setData() {
        ControllerInterface ci = new SystemController();
        var memberList = ci.allMembers();

        List<CheckoutRecordEntry> entries = new ArrayList<>();
        for (LibraryMember member : memberList) {
            var list = DataAccessFacade.getCheckoutRecordEntries(member.getMemberId());
            entries.addAll(list);
        }
        checkoutRecordEntries = FXCollections.observableArrayList(
                entries
        );
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getIsbn()));
        title.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getTitle()));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBookCopy().getCopyNum())));
        checkoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        memberId.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCheckoutRecord().getLibraryMember().getMemberId())));
        memberName.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCheckoutRecord().getLibraryMember().getFirstAndLastName())));
        table.setItems(checkoutRecordEntries);
    }

    @FXML
    public void printCheckoutRecord(ActionEvent actionEvent) {

        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("ISBN", "Title", "Copy Num", "Member Id", "Member Name", "Checkout Date", "Due Date");
        asciiTable.addRule();
        checkoutRecordEntries.forEach(checkoutRecordEntry -> {
            asciiTable.addRow(
                    checkoutRecordEntry.getBookCopy().getBook().getIsbn(),
                    checkoutRecordEntry.getBookCopy().getBook().getTitle(),
                    checkoutRecordEntry.getBookCopy().getCopyNum(),
                    checkoutRecordEntry.getCheckoutRecord().getLibraryMember().getMemberId(),
                    checkoutRecordEntry.getCheckoutRecord().getLibraryMember().getFirstAndLastName(),
                    checkoutRecordEntry.getCheckoutDate(),
                    checkoutRecordEntry.getDueDate()
            );
            asciiTable.addRule();
        });
        System.out.println(asciiTable.render());
    }

    public void onBackPressed(MouseEvent mouseEvent) {
//        try {
//            LibrarianWindow.INSTANCE.init();
            AllCheckoutRecordTableWindow.INSTANCE.hide();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
