import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {
    private String name;
    private String issn;
    private int frequency;
    private double issuePrice;
    private ArrayList<Subscription> subscriptions;

    public Journal(String name, String issn, int frequency, double issuePrice) {
        this.name = name;
        this.issn = issn;
        this.frequency = frequency;
        this.issuePrice = issuePrice;
        subscriptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(double issuePrice) {
        this.issuePrice = issuePrice;
    }

    public boolean addSubscription(Subscription subscription) {
        if (subscription != null) {
            subscriptions.add(subscription);
            subscription.setCopies(subscription.getCopies() + 1);
            return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return "Journal Name : " + name + '\n' + "ISSN :" + issn + '\n' + "Frequency : " + frequency + '\n' + "Price : " + issuePrice + " $\n";
    }
}
