package validator;

import java.util.HashMap;
import java.util.Map;
import domain.Entity;
import domain.Publication;
import domain.Subscription;
import domain.User;

public class ValidatorFactory {
    private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

    static {
        validators.put(User.class, UserValidator.class);
        validators.put(Publication.class, PublicationValidator.class);
        validators.put(Subscription.class, SubscriptionValidator.class);
    }

    @SuppressWarnings("unchecked")
    public static <Type extends Entity> Validator<Type> createValidator(Class<Type> entity) {
        try {
            return (Validator<Type>) validators.get(entity).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
