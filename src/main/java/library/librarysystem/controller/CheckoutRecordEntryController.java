package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.librarysystem.business.CheckoutRecordEntry;
import library.librarysystem.dataaccess.DataAccessFacade;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutRecordEntryController implements Initializable {

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

//    public ObservableList<CheckoutRecordEntry> list = FXCollections.observableArrayList(
//            new CheckoutRecordEntry(LocalDate.now(), LocalDate.now(),
//                    new BookCopy(new Book("231-213", "Hello World",
//                            0, List.of(new Author("Joe", "Thomas", "641-445-2123",
//                            new Address("57 kilvert", "KTM", "GA", "34234"),
//                            "A happy man is he."))), 1))
//    );

    public ObservableList<CheckoutRecordEntry> list = FXCollections.observableArrayList(
            DataAccessFacade.getCheckoutRecordEntries(LibrarianController.currentMemberId)
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getIsbn()));
        title.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookCopy().getBook().getTitle()));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBookCopy().getCopyNum())));
        checkoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        table.setItems(list);
    }
}
