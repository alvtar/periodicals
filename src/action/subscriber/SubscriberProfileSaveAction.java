package action.subscriber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import action.Action;
import action.AuthorizedUserAction;
import service.UserService;
import validator.Validator;
import validator.ValidatorFactory;
import domain.User;
import exception.IncorrectFormDataException;
import exception.PersistentException;


public class SubscriberProfileSaveAction extends AuthorizedUserAction {
    private static Logger logger = Logger.getLogger(SubscriberProfileSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/publication/subscriber_list.html");
        HttpSession session = request.getSession();

        try {
            Validator<User> validator = ValidatorFactory.createValidator(User.class);
            User user = validator.validate(request);
            UserService service = factory.getService(UserService.class);

            String referer = request.getHeader("Referer");
            if (referer.contains("subscriber_register")) {

                Boolean unique = service.isUniqueLogin(user.getLogin());

                if (!unique) {
                    forward = new Forward("/profile/subscriber_register.html");
                    forward.getAttributes().put("message", "Такой логин уже существует, выберите другой");
                    logger.warn(String.format("Login is exists when create user \"%s\" ", user.getLogin()));
                    user.setLogin(null);
                    user.setPassword(null);
                    session.setAttribute("authorizedUser", user);
                    return forward;
                }
            }

            service.save(user);
            setAuthorizedUser(user);

            session.setAttribute("authorizedUser", user);
            forward.getAttributes().put("id", user.getId());
            forward.getAttributes().put("message", "Данные пользователя успешно сохранены");
            logger.info(
                    String.format("User \"%s\" saved user with id %d", getAuthorizedUser().getLogin(), user.getId()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user",
                    getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
