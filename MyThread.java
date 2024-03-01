import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class MyThread extends Thread {
    private int month, year;
    private Distributor distributor;
    private StringBuilder string;
    private JFrame frame;

    public MyThread(int month, int year, Distributor distributor, JFrame frame) {
        this.month = month;
        this.year = year;
        this.distributor = distributor;
        string = new StringBuilder();
        this.frame = frame;
    }

    @Override
    public void run() {
        int count = 0;
        Vector<Vector<String>> stringList = new Vector<>();
        for (String key : distributor.getJournals().keySet()) {
            for (Subscription s : distributor.getJournals().get(key).getSubscriptions()) {
                if (s.getDates().getStartYear() == year && month >= s.getDates().getStartMonth()) {
                    Vector<String> temp = new Vector<>();
                    temp.add(s.getSubscriber().getName());
                    if (s.getSubscriber() instanceof Individual) {
                        temp.add("Individual");
                    } else {
                        temp.add("Corporation");
                    }
                    temp.add(s.getSubscriber().getAddress());
                    temp.add(String.valueOf(s.getDates().getStartMonth()) + " / " + s.getDates().getStartYear());
                    if (s.getDates().getStartMonth() == 1) {
                        temp.add(String.valueOf(s.getDates().getEndMonth()) + " / " + s.getDates().getStartYear());
                    } else {
                        temp.add(String.valueOf(s.getDates().getEndMonth()) + " / " + (s.getDates().getStartYear() + 1));
                    }
                    temp.add(s.getJournal().getName());
                    double price = s.getJournal().getIssuePrice() * s.getJournal().getFrequency() * s.getCopies() * ((100 - s.getPayment().getDiscountRatio()) / 100.0);
                    temp.add(String.valueOf(price) + " $");
                    stringList.add(temp);
                } else if (s.getDates().getStartYear() + 1 == year && month < s.getDates().getEndMonth() && s.getDates().getEndMonth() != 12) {
                    Vector<String> temp = new Vector<>();
                    temp.add(s.getSubscriber().getName());
                    if (s.getSubscriber() instanceof Individual) {
                        temp.add("Individual");
                    } else {
                        temp.add("Corporation");
                    }
                    temp.add(s.getSubscriber().getAddress());
                    temp.add(String.valueOf(s.getDates().getStartMonth()) + " / " + s.getDates().getStartYear());
                    if (s.getDates().getStartMonth() == 1) {
                        temp.add(String.valueOf(s.getDates().getEndMonth()) + " / " + s.getDates().getStartYear());
                    } else {
                        temp.add(String.valueOf(s.getDates().getEndMonth()) + " / " + (s.getDates().getStartYear() + 1));
                    }
                    temp.add(s.getJournal().getName());
                    double price = s.getJournal().getIssuePrice() * s.getJournal().getFrequency() * s.getCopies() * ((100 - s.getPayment().getDiscountRatio()) / 100.0);
                    temp.add(String.valueOf(price) + " $");
                    stringList.add(temp);
                }
            }
        }

        DefaultTableModel tableModel = new DefaultTableModel(0, 7); // 0 rows, 5 columns
        tableModel.setColumnIdentifiers(new Object[]{"Subscriber Name", "Subscriber Type", "Subscriber Address", "Start Date", "End Date", "Journal Name", "Annual Payment"});
        JTable table = new JTable(tableModel);

        stringList.forEach(tableModel::addRow);

        JScrollPane jScrollPane = new JScrollPane(table);
        frame.getContentPane().add(jScrollPane);

        frame.setSize(850, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        table.setPreferredSize(new Dimension(850, 400));
        table.setRowHeight(30);

    }
}