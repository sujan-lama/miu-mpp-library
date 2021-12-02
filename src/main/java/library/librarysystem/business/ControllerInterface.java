package library.librarysystem.business;

import java.util.List;


public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;

    boolean isBookAvailable(String memberId, String isbn);

    void createCheckOutRecordEntry(String memberId, String isbn);

    LibraryMember getMemberById(String memberId);

    public void logout();

    public List<LibraryMember> allMembers();

    public List<String> allBookIds();

    public void saveMember(LibraryMember member);

    public void deleteMember(LibraryMember member);

    public void editMember(LibraryMember member);
}
