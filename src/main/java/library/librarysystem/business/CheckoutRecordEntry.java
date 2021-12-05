package library.librarysystem.business;

import java.io.Serializable;
import java.time.LocalDate;

final public class CheckoutRecordEntry implements Serializable {

    private static final long serialVersionUID = -63976228084869815L;

    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private CheckoutRecord checkoutRecord;

    public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.checkoutRecord = checkoutRecord;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }
}
