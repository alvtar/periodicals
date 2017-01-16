package action.subscriber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import action.Action;
import domain.Subscription;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.SubscriptionService;
import validator.Validator;
import validator.ValidatorFactory;

public class SubscriptionSaveAction extends SubscriberAction {
    private static Logger logger = Logger.getLogger(SubscriptionSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/subscription/user_list.html");

        try {
            Validator<Subscription> validator = ValidatorFactory.createValidator(Subscription.class);
            Subscription subscription = validator.validate(request);
            SubscriptionService service = factory.getService(SubscriptionService.class);
            service.save(subscription);
            forward.getAttributes().put("message", "Данные подписки успешно сохранены");
            logger.info(String.format("User \"%s\" saved subscription with id %d", getAuthorizedUser().getLogin(),
                    subscription.getId()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save subscription",
                    getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
