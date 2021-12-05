package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.BookCopy;
import library.librarysystem.business.CheckoutRecordEntry;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.OverdueTableWindow;
import library.librarysystem.ui.OverdueWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverdueTableWindowController extends Stage {

    @FXML
    public Label label;
    @FXML
    private TableView<BookCopy> table;
    @FXML
    private TableColumn<BookCopy, String> isbn;
    @FXML
    private TableColumn<BookCopy, String> titleField;
    @FXML
    private TableColumn<BookCopy, String> copyNum;
    @FXML
    private TableColumn<BookCopy, String> libMember;
    @FXML
    private TableColumn<BookCopy, String> dueDate;

    @FXML
    private Label memberId;
    @FXML
    private Label memberName;

    public ObservableList<BookCopy> list;


    public void setData(Book book) {
        list = FXCollections.observableArrayList(
                DataAccessFacade.getBooksByIsbn(book.getIsbn())
        );
        label.setText("Overdue table for Book: " + book.getTitle());
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getIsbn())));
        titleField.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getTitle())));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCopyNum())));
        libMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        table.setItems(list);
    }

    public void onBackPressed(ActionEvent event) {
        OverdueTableWindow.INSTANCE.hide();
        try {
            OverdueWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
