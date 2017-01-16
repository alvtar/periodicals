package action.subscriber;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import domain.Role;
import domain.Subscription;
import domain.User;
import exception.PersistentException;
import service.SubscriptionService;

public class SubscriptionUserListAction extends SubscriberAction {

    public SubscriptionUserListAction() {
        getAllowRoles().add(Role.SUBSCRIBER);
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        SubscriptionService service = factory.getService(SubscriptionService.class);

        User authorizedUser = getAuthorizedUser();

        List<Subscription> subscriptions = service.findByUserId(authorizedUser.getId());

        request.setAttribute("subscriptions", subscriptions);
        return null;
    }
}