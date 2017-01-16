package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import domain.Publication;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.PublicationService;
import validator.Validator;
import validator.ValidatorFactory;

public class PublicationSaveAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(PublicationSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/publication/list.html");

        try {
            Validator<Publication> validator = ValidatorFactory.createValidator(Publication.class);
            Publication publication = validator.validate(request);
            PublicationService service = factory.getService(PublicationService.class);
            service.save(publication);
            forward.getAttributes().put("id", publication.getId());
            forward.getAttributes().put("message", "Данные издания успешно сохранены");
            logger.info(String.format("User \"%s\" saved successfully with id %d", getAuthorizedUser().getLogin(),
                    publication.getId()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save publication",
                    getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
