package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import action.Action;
import exception.PersistentException;
import service.PublicationService;

public class PublicationDeleteAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(PublicationDeleteAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/publication/list.html");
        try {
            PublicationService service = factory.getService(PublicationService.class);
            Integer id = Integer.parseInt(request.getParameter("id"));
            service.delete(id);
            forward.getAttributes().put("message", "Издание успешно удалёно");
            logger.info(
                    String.format("User \"%s\" deleted publication with id %d", getAuthorizedUser().getLogin(), id));
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete publication",
                    getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}