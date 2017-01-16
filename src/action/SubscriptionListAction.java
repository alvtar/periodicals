package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.admin.AdministratorAction;
import domain.Role;
import domain.Subscription;
import exception.PersistentException;
import service.SubscriptionService;

public class SubscriptionListAction extends AdministratorAction {

    public SubscriptionListAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        SubscriptionService service = factory.getService(SubscriptionService.class);
        List<Subscription> subscriptions = service.findAll();
        request.setAttribute("subscriptions", subscriptions);
        return null;
    }
}