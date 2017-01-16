package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Publication;
import exception.IncorrectFormDataException;

public class PublicationValidator implements Validator<Publication> {
    @Override
    public Publication validate(HttpServletRequest request) throws IncorrectFormDataException {

        Publication publication = new Publication();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                publication.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("issn");
        if (parameter != null && !parameter.isEmpty()) {
            publication.setIssn(Integer.parseInt(parameter));
        } else {
            throw new IncorrectFormDataException("issn", parameter);
        }
        parameter = request.getParameter("title");
        if (parameter != null && !parameter.isEmpty()) {
            publication.setTitle(parameter);
        } else {
            throw new IncorrectFormDataException("title", parameter);
        }

        parameter = request.getParameter("monthCost");
        if (parameter != null && !parameter.isEmpty()) {
            publication.setMonthCost(Float.parseFloat(parameter));
        } else {
            throw new IncorrectFormDataException("monthCost", parameter);
        }

        parameter = request.getParameter("active");
        if (parameter != null && !parameter.isEmpty()) {
            publication.setActive(true);
        } else {
            publication.setActive(false);
        }

        return publication;
    }

}
