package library.librarysystem.business;

import java.util.List;


public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;

    public List<String> allMemberIds();

    public List<String> allBookIds();

    public void saveMember(LibraryMember member);

    boolean isBookAvailable(String memberId, String isbn);

    void createCheckOutRecordEntry(String memberId, String isbn);
}
