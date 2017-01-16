package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Role;
import exception.PersistentException;

public class PublicationSearchAction extends AuthorizedUserAction {

    public PublicationSearchAction() {
        getAllowRoles().add(Role.SUBSCRIBER);
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return null;
    }
}
