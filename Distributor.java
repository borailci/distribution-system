import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;

public class Distributor {
    private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;

    public Distributor() {
        journals = new Hashtable<>();
        subscribers = new Vector<>();
    }

    public static void main(String[] args) {
  /*
        Journal journal = new Journal("Journal1", "1000", 1, 1);
        Journal journal2 = new Journal("Journal2", "1001", 1, 1);
        Journal journal3 = new Journal("Journal3", "1002", 1, 1);
        Distributor distributor = new Distributor();
        distributor.addJournal(journal);
        distributor.addJournal(journal2);
        distributor.addJournal(journal3);
        Individual individual = new Individual("Bora İLCİ", "UK","123456",11,24,123);
        Individual individual1 = new Individual("Asude CAKAL", "UT","123456",11,24,123);


        distributor.addSubscriber(individual);
        distributor.addSubscriber(individual1);
        distributor.saveState("test.dat");
        distributor.loadState("test.dat");

   */

    }

    public boolean addJournal(Journal journal) {

        if (!journals.containsKey(journal.getIssn())) {
            journals.put(journal.getIssn(), journal);
            return true;
        }
        return false;
    }

    public Journal searchJournal(String issn) {
        if (journals.get(issn) != null) return journals.get(issn);
        else {
            System.out.println(issn + " journal is not in the list!");
            return null;
        }
    }

    public boolean addSubscriber(Subscriber subscriber) {
        return subscribers.add(subscriber);
    }

    public Subscriber searchSubscriber(String name) {
        for (Subscriber s : subscribers) {
            if (name.compareTo(s.getName()) == 0) {
                return s;
            }
        }
        return null;
    }

    public boolean addSubscription(String issn, Subscriber subscriber, Subscription subscription) {
        if (subscription == null) return false;
        if (subscriber == null) return false;
        if (searchJournal(issn) == null) return false;
        searchJournal(issn).addSubscription(subscription);
        return true;
    }

    public String listAllSendingOrders(int month, int year) {
        StringBuilder string = new StringBuilder();
        for (String key : journals.keySet()) {
            for (Subscription sub : journals.get(key).getSubscriptions()) {
                if (year == sub.getDates().getStartYear() && month >= sub.getDates().getStartMonth()) {
                    int monthCount = month - sub.getDates().getStartMonth() + 1;
                    if (monthCount < (12 / journals.get(key).getFrequency()))
                        return sub.getSubscriber().getName() + " should have 0 copies of " + sub.getJournal().getName() + "\n";
                    int copies = (monthCount / (12 / journals.get(key).getFrequency())) * sub.getCopies();
                    double amount = copies * journals.get(key).getIssuePrice() * ((100 - sub.getPayment().getDiscountRatio()) / 100.0);
                    if (sub.canSend(month)) {
                        string.append(sub.getSubscriber().getName()).append(" should have received ").append(copies).append(" copies of ").append(sub.getJournal().getName()).append(". ").append(amount).append(" $ should have received from the subscriber.\n");
                    } else {
                        string.append(sub.getSubscriber().getName()).append(" does not have enough payment to receive! Missing amount : ").append(amount - sub.getPayment().getReceivedPayment()).append(" $\n");
                    }
                } else if (year == sub.getDates().getStartYear() + 1 && month <= sub.getDates().getEndMonth()) {
                    int monthCount = 0;
                    if (month < sub.getDates().getStartMonth()) {
                        monthCount = 12 - (sub.getDates().getStartMonth() - month) + 1;
                        int copies = (monthCount / (12 / journals.get(key).getFrequency())) * sub.getCopies();
                        double amount = copies * journals.get(key).getIssuePrice() * ((100 - sub.getPayment().getDiscountRatio()) / 100.0);
                        if (sub.canSend(month)) {
                            string.append(sub.getSubscriber().getName()).append(" should have received ").append(copies).append(" copies of ").append(sub.getJournal().getName()).append(". ").append(amount).append(" $ should have received from the subscriber.\n");
                        } else {
                            string.append(sub.getSubscriber().getName()).append(" does not have enough payment to receive! Missing amount : ").append(amount - sub.getPayment().getReceivedPayment()).append(" $\n");
                        }
                    }

                }
            }
        }
        return string.toString();
    }

    public String listSendingOrders(String issn, int month, int year) {

        StringBuilder string = new StringBuilder();
        string.append("                                                                    # Sending Order List #\n");
        Journal j = journals.get(issn);
        for (Subscription s : j.getSubscriptions()) {
            if (year == s.getDates().getStartYear() && month >= s.getDates().getStartMonth()) {
                int monthCount = month - s.getDates().getStartMonth() + 1;
                if (monthCount < (12 / j.getFrequency()))
                    return s.getSubscriber().getName() + " should have 0 copies of " + s.getJournal().getName() + "\n";
                int copies = (monthCount / (12 / j.getFrequency())) * s.getCopies();
                double amount = copies * j.getIssuePrice() * ((100 - s.getPayment().getDiscountRatio()) / 100.0);
                if (s.canSend(month)) {
                    string.append(s.getSubscriber().getName()).append(" should have received ").append(copies).append(" copies of ").append(s.getJournal().getName()).append(". ").append(amount).append(" $ should have received from the subscriber.\n");
                } else {
                    string.append(s.getSubscriber().getName()).append(" does not have enough payment to receive! Missing amount : ").append(amount - s.getPayment().getReceivedPayment()).append(" $\n");
                }
            } else if (year == s.getDates().getStartYear() + 1 && month <= s.getDates().getEndMonth()) {
                int monthCount = 0;
                if (month < s.getDates().getStartMonth()) {
                    monthCount = 12 - (s.getDates().getStartMonth() - month) + 1;
                    int copies = (monthCount / (12 / j.getFrequency())) * s.getCopies();
                    double amount = copies * j.getIssuePrice() * ((100 - s.getPayment().getDiscountRatio()) / 100.0);
                    if (s.canSend(month)) {
                        string.append(s.getSubscriber().getName()).append(" should have received ").append(copies).append(" copies of ").append(s.getJournal().getName()).append(". ").append(amount).append(" $ should have received from the subscriber.\n");
                    } else {
                        string.append(s.getSubscriber().getName()).append(" does not have enough payment to receive! Missing amount : ").append(amount - s.getPayment().getReceivedPayment()).append(" $\n");
                    }
                }

            }
        }
        return string.toString();
    }

    public String listIncompletePayments() {
        StringBuilder string = new StringBuilder();
        int count = 1;
        for (String key : journals.keySet()) {
            for (Subscription s : journals.get(key).getSubscriptions()) {
                double price = journals.get(key).getIssuePrice() * journals.get(key).getFrequency() * s.getCopies() * ((100 - s.getPayment().getDiscountRatio()) / 100.0);
                if (s.getPayment().getReceivedPayment() < price) {
                    string.append("                               ").append(count).append(". Incomplete Payment\n");
                    string.append("# ").append(s.getSubscriber().getName()).append(" has not paid 1-year payment yet!\n");
                    string.append("# Required 1-year payment : ").append(price).append("  Received payment : ").append(s.getPayment().getReceivedPayment()).append("\n");
                    count++;
                }
            }
        }
        return string.toString();
    }

    public Hashtable<String, Journal> getJournals() {
        return journals;
    }

    public void setJournals(Hashtable<String, Journal> journals) {
        this.journals = journals;
    }

    public Vector<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Vector<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    // GUI de gösterebilmek için string döndürüyorum
    public String listSubscriptions(String subscriberName) {
        if (searchSubscriber(subscriberName) == null) return "Subscriber not found!";
        StringBuilder string = new StringBuilder();
        for (String key : journals.keySet()) {
            System.out.println(key);
            System.out.println(journals.get(key).getSubscriptions());
            for (Subscription s : journals.get(key).getSubscriptions()) {
                System.out.println(s);
                if (subscriberName.equals(s.getSubscriber().getName())) {
                    string.append(s.toString()).append("\n");
                }
            }
        }
        System.out.println(string);
        return string.toString();
    }

    public String listSubscriptionsForJournal(String issn) {
        if (searchJournal(issn) == null) {
            return "Journal not found!";
        }
        StringBuilder string = new StringBuilder();
        for (Subscription s : searchJournal(issn).getSubscriptions()) {
            string.append(s.toString()).append("\n");
        }
        return string.toString();
    }
    // GUI da yazdırabilmek için string döndürüyorum

    public boolean saveState(String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(journals);
            obj.writeObject(subscribers);
            obj.close();
            file.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred!");
            throw new RuntimeException(e);
        }
    }

    public String loadState(String fileName) {
        try {
            StringBuilder string = new StringBuilder();
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream obj = new ObjectInputStream(file);
            journals = (Hashtable<String, Journal>) obj.readObject();
            subscribers = (Vector<Subscriber>) obj.readObject();
            string.append("Journals\n");
            for (String key : journals.keySet()) {
                string.append(journals.get(key));
            }
            string.append("Subscribers\n");
            for (Subscriber s : subscribers) {
                string.append(s);
            }
            obj.close();
            file.close();
            return string.toString();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred!");
            throw new RuntimeException(e);
        }

    }

    public void report(int month, int year) {
        JFrame frame = new JFrame("Report Table");
        frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
        frame.getContentPane().setBackground(new Color(118, 217, 213));
        frame.setLayout(new GridLayout(1, 2, 0, 0));

        MyThread thread = new MyThread(month, year, this, frame);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
