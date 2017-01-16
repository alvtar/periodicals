package action.subscriber;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import domain.Publication;
import domain.Role;
import domain.User;
import exception.PersistentException;
import service.PublicationService;

public class SubscriptionAddAction extends SubscriberAction {

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        request.setAttribute("roles", Role.values());
        HttpSession session = request.getSession();

        try {
            User user = (User) request.getAttribute("authorizedUser"); ////////
            Publication publication = new Publication();
            String referer = request.getHeader("Referer");
            if (referer.contains("search/publication/form")) {
                publication = (Publication) session.getAttribute("publication");
            } else {
                Integer id = (Integer) request.getAttribute("id");
                if (id == null) {
                    id = Integer.parseInt(request.getParameter("id"));
                }
                PublicationService service = factory.getService(PublicationService.class);
                publication = service.findById(id);
            }

            if (publication != null) {
                request.setAttribute("publication", publication);
                request.setAttribute("user", user); ////////

                Calendar calendar = new GregorianCalendar();

                int realYear = calendar.get(Calendar.YEAR);

                realYear = calendar.get(Calendar.YEAR);
                int realMonth = calendar.get(Calendar.MONTH) + 1;

                List<Integer> years = new ArrayList<Integer>();

                if (realMonth < 12)
                    years.add(realYear);
                if (realMonth > 9)
                    years.add(realYear + 1);

                request.setAttribute("years", years); 

                request.setAttribute("realYear", realYear); 

                for (int i = 1; i <= realMonth; i++) {
                    request.setAttribute("month" + i, false); 
                }

            }

        } catch (NumberFormatException e) {
        }

        return null;
    }

}