package action.subscriber;

import action.Action;
import domain.Role;

abstract public class SubscriberAction extends Action {
    public SubscriberAction() {
        getAllowRoles().add(Role.SUBSCRIBER);
    }
}
