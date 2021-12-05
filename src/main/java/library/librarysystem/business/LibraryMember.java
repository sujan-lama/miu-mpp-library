package library.librarysystem.business;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


final public class LibraryMember extends Person implements Serializable {
    private String memberId;
    private CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
    }


    public String getMemberId() {
        return memberId;
    }

    public boolean isEmpty() {
        return getMemberId().isEmpty() || getFirstName().isEmpty() || getLastName().isEmpty() || getTelephone().isEmpty() || getAddress().isEmpty();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryMember member = (LibraryMember) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getTelephone() + " " + getAddress();
    }

    @Serial
    private static final long serialVersionUID = -2226197306790714013L;
}
