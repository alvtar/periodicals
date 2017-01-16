package validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.Publication;
import domain.Subscription;
import domain.User;
import exception.IncorrectFormDataException;

public class SubscriptionValidator implements Validator<Subscription> {
    @Override
    public Subscription validate(HttpServletRequest request) throws IncorrectFormDataException {

        Subscription subscription = new Subscription();

        User user = new User();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        user = (User) session.getAttribute("authorizedUser");
        subscription.setUser(user);

        Publication publication = new Publication();
        String parameter = request.getParameter("id");
        if (parameter != null && !parameter.isEmpty()) {
            publication.setId(Integer.parseInt(parameter));
            subscription.setPublication(publication);
        } else {
            throw new IncorrectFormDataException("publicationId", parameter);
        }

        parameter = request.getParameter("subsYear");
        if (parameter != null && !parameter.isEmpty()) {
            subscription.setSubsYear(Integer.parseInt(parameter));
        } else {
            throw new IncorrectFormDataException("subsYear", parameter);
        }

        int tempMonths = 0;
        int sumMonths = 0;

        for (int m = 1; m <= 12; m++) {
            parameter = request.getParameter("month" + m);
            if (parameter != null && !parameter.isEmpty()) {
                tempMonths = (int) (tempMonths + Math.pow(2, (m - 1)));
                sumMonths++;
            }
        }
        subscription.setSubsMonths(tempMonths);

        parameter = request.getParameter("monthCost");
        if (parameter != null && !parameter.isEmpty()) {
            float tempCost = sumMonths * Float.parseFloat(parameter);
            subscription.setPaymentSum(tempCost);

        } else {
            throw new IncorrectFormDataException("monthCost", parameter);
        }

        parameter = request.getParameter("paymentSum");
        if (parameter != null && !parameter.isEmpty()) {
            subscription.setPaymentSum(Float.parseFloat(parameter));
        } else {
            //throw new IncorrectFormDataException("paymentSum", parameter);
        }

        return subscription;
    }

}