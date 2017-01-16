package domain;

import java.util.Date;

public class Subscription extends Entity {
    private Date regDate;
    private User user;
    private Publication publication;
    private Integer subsYear;
    private Integer subsMonths;
    private Float paymentSum;

    public Subscription() {
        super();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Integer getSubsYear() {
        return subsYear;
    }

    public void setSubsYear(Integer subsYear) {
        this.subsYear = subsYear;
    }

    public Integer getSubsMonths() {
        return subsMonths;
    }

    public void setSubsMonths(Integer subsMonths) {
        this.subsMonths = subsMonths;
    }

    public Float getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Float paymentSum) {
        this.paymentSum = paymentSum;
    }

    // Convert decimal value of months to binary
    public String getMonthsBinaryArray() {
        StringBuilder sb = new StringBuilder();
        int x = 1;
        for (int i = 0; i < 12; i++) {

            sb.append((subsMonths & x) == 0 ? "" : (i + 1) + " ");

            x <<= 1;
        }
        return sb.toString();
    }

}
