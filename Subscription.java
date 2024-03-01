import java.io.Serializable;

public class Subscription implements Serializable {
    private final DateInfo dates;
    private final Subscriber subscriber;
    private final Journal journal;
    private PaymentInfo payment;
    private int copies;

    public Subscription(DateInfo dates, PaymentInfo payment, int copies, Subscriber subscriber, Journal journal) {
        this.dates = dates;
        this.copies = copies;
        this.subscriber = subscriber;
        this.journal = journal;
        this.payment = payment;
    }

    public DateInfo getDates() {
        return dates;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public void setPayment(PaymentInfo payment) {
        this.payment = payment;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public Journal getJournal() {
        return journal;
    }

    public void acceptPayment(double amount) {
        payment.increasePayment(amount);
    }

    public boolean canSend(int issueMonth) {
        int copiesBoughtSoFar;
        if (issueMonth > getDates().getStartMonth()) {
            copiesBoughtSoFar = (issueMonth - getDates().getStartMonth() + 1) / (12 / getJournal().getFrequency());
        } else
            copiesBoughtSoFar = (12 - (getDates().getStartMonth() - issueMonth) + 1) / (12 / getJournal().getFrequency());
        copiesBoughtSoFar *= getCopies();
        double requiredPayment = copiesBoughtSoFar * (getJournal().getIssuePrice()) * ((100.0 - getPayment().getDiscountRatio()) / 100.0);
        System.out.println("Required payment : " + requiredPayment);
        if (requiredPayment <= getPayment().getReceivedPayment()) {
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "\n***********************\nSubscription Information\n" + subscriber + '\n' + dates + '\n' + payment + '\n' + journal + '\n' + "Copy count : " + copies + "\n***********************\n";
    }
}
