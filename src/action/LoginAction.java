package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import service.UserService;
import domain.Role;
import domain.User;
import exception.PersistentException;

public class LoginAction extends Action {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    protected static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

    static {
        menu.put(Role.SUBSCRIBER,
                new ArrayList<>(Arrays.asList(new MenuItem("/publication/subscriber_list.html", "издания"), // +++
                        new MenuItem("/search/publication/form.html", "поиск изданий"), // +++
                        new MenuItem("/subscription/user_list.html", "свои подписки") //

                )));
        menu.put(Role.ADMINISTRATOR,
                new ArrayList<>(Arrays.asList(new MenuItem("/publication/list.html", "издания"), // +++
                        new MenuItem("/user/list.html", "пользователи"), // +++
                        new MenuItem("/subscription/list.html", "подписки") //

                )));

    }

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", null);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            if (user != null) {
                session.setAttribute("authorizedUser", user);
                session.setAttribute("menu", menu.get(user.getRole()));
                logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(),
                        request.getRemoteHost(), request.getRemotePort()));
                return new Forward("/index.html");
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не опознанны");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login,
                        request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return null;
    }
}
