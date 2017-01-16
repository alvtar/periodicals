package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import domain.Publication;
import domain.Role;
import exception.PersistentException;
import service.PublicationService;

public class PublicationEditAction extends AdministratorAction {

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        request.setAttribute("roles", Role.values());
        try {
            Integer id = (Integer) request.getAttribute("id");
            if (id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            PublicationService service = factory.getService(PublicationService.class);
            Publication publication = service.findById(id);
            if (publication != null) {
                request.setAttribute("publication", publication);
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }

}