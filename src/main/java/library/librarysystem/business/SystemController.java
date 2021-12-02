package library.librarysystem.business;

import library.librarysystem.dataaccess.Auth;
import library.librarysystem.dataaccess.DataAccess;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.dataaccess.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();

    }

    @Override
    public List<String> allMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> allBookIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }

    @Override
    public void saveMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(member);
    }
}
