package action.subscriber;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.AuthorizedUserAction;
import domain.Publication;
import domain.Role;
import exception.PersistentException;
import service.PublicationService;

public class PublicationSubscriberListAction extends AuthorizedUserAction {

    public PublicationSubscriberListAction() {
        getAllowRoles().add(Role.SUBSCRIBER);
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        PublicationService service = factory.getService(PublicationService.class);
        List<Publication> publications = service.findAll();
        request.setAttribute("publications", publications);
        return null;
    }
}
