package library.librarysystem.business;

import java.io.Serializable;
import java.util.Date;

final public class CheckoutRecordEntry implements Serializable {
    private Date checkoutDate;
    private Date dueDate;
    private BookCopy bookCopy;

    public CheckoutRecordEntry(Date checkoutDate, Date dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }
}
