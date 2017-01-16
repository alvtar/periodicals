package action.subscriber;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import domain.Role;
import domain.User;
import exception.PersistentException;

public class SubscriberRegisterAction extends Action {

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {

        HttpSession session = request.getSession();

        String referer = request.getHeader("Referer");
        if (referer.contains("login")) {
            session.setAttribute("authorizedUser", null);
        }

        User oldUser = new User();

        oldUser = (User) session.getAttribute("authorizedUser");
        if (oldUser == null) {
            User user = new User();
            user.setRole(Role.SUBSCRIBER);
            request.setAttribute("roleName", Role.SUBSCRIBER);
            request.setAttribute("role", Role.SUBSCRIBER.getId());
            session.setAttribute("authorizedUser", user);
        } else {
            session.setAttribute("authorizedUser", oldUser);
        }

        return null;
    }

}
