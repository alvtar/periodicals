package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.Publication;
import domain.Role;
import exception.PersistentException;
import service.PublicationService;

public class PublicationSearchResultAction extends AuthorizedUserAction {

    public PublicationSearchResultAction() {
        getAllowRoles().add(Role.SUBSCRIBER);
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        PublicationService service = factory.getService(PublicationService.class);
        HttpSession session = request.getSession();

        String issn = request.getParameter("issn");
        String title = request.getParameter("title");
        if (issn != null && issn.matches("^\\d{3,8}$")) {

            Publication publication = service.findByIssn(Integer.parseInt(issn));
            if (publication != null) {
                Forward forward = new Forward("/subscription/add.html");
                session.setAttribute("publication", publication);
                return forward;
            }

        } else {
            if (title != null) {
                List<Publication> publications = service.findByTitleLike("%" + title + "%");
                request.setAttribute("publications", publications);
            }
        }

        return null;
    }
}
