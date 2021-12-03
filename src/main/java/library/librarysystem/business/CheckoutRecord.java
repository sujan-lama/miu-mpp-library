package library.librarysystem.business;

import java.io.Serializable;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = -63976228084869815L;

    private List<CheckoutRecordEntry> checkoutRecordEntries;

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    public void setCheckoutRecordEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
        this.checkoutRecordEntries = checkoutRecordEntries;
    }
}
