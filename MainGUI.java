import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MainGUI extends JFrame {
    private JPanel panel1;
    private JButton createSubscriptionButton;
    private JButton createSubscriberButton;
    private JButton searchSubscriberButton;
    private JButton listAllSendingOrdersButton;
    private JButton createJournalButton;
    private JButton searchJournalButton;
    private JPanel panel2;
    private JButton listSendingOrdersButton;
    private JButton saveStateButton;
    private JButton loadStateButton;
    private JButton listSubscriptionsButton;
    private JButton reportStateButton;
    private JButton listIncompletePaymentsButton;
    private JButton AcceptPaymentButton;
    private JButton addSubscriptionButton;
    private Distributor distributor;

    public MainGUI(Distributor distributor) {
        this.distributor = distributor;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("adsiz2.png").getImage());
        this.setSize(700, 520);
        this.setLocation(550, 220);
        setTitle("Socrates Distribution System");
        setContentPane(panel1);
        setVisible(true);
        this.pack();
        createJournalButton.addActionListener(new ActionListener() {

            String name, ISSN;
            int frequency;
            double issuePrice;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Create");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(5, 1, 20, 10));
                frame.setSize(300, 250);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField();
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();
                TextField textField3 = new TextField();
                textField.setText("Enter name");
                textField1.setText("Enter ISSN");
                textField2.setText("Enter frequency");
                textField3.setText("Enter issuePrice");

                //textField.setBounds(100, 100, 100, 50);

                JButton button = new JButton("Apply");
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        name = textField.getText();
                        ISSN = textField1.getText();
                        frequency = Integer.parseInt(textField2.getText());
                        issuePrice = Double.parseDouble(textField3.getText());
                        Journal journal = new Journal(name, ISSN, frequency, issuePrice);
                        if (distributor.addJournal(journal)) {
                            JOptionPane.showMessageDialog(null, "Journal is successfully created and added to the list!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "An error is occurred!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.dispose();
                            return;
                        }

                    }

                });

                frame.add(textField);
                frame.add(textField1);
                frame.add(textField2);
                frame.add(textField3);
                frame.add(button);


            }
        });
        searchJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Search");
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.setLayout(new GridLayout(2, 1, 40, 10));
                frame.setSize(250, 125);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField();
                textField.setText("Enter ISSN");
                JButton button = new JButton();
                button.setText("Search Journal");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String ISSN = textField.getText();
                        Journal journal = distributor.searchJournal(ISSN);
                        if (journal != null) {
                            JOptionPane.showMessageDialog(null, "Journal #" + ISSN + "\n" + journal.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "Journal #" + ISSN + " could not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.dispose();
                            return;
                        }

                    }
                });
                frame.add(textField);
                frame.add(button);
            }
        });
        createSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Create");
                frame1.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame1.getContentPane().setBackground(new Color(118, 217, 213));
                frame1.setLayout(new GridLayout(2, 1, 40, 10));
                frame1.setSize(250, 125);
                frame1.setVisible(true);
                frame1.setLocationRelativeTo(null);
                JButton button = new JButton("Individual");
                JButton button1 = new JButton("Corporation");

                frame1.add(button);
                frame1.add(button1);

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        JFrame frame = new JFrame();
                        frame.setTitle("Create Individual");
                        frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                        frame.getContentPane().setBackground(new Color(118, 217, 213));
                        frame.setLayout(new GridLayout(7, 1, 40, 10));
                        frame.setSize(400, 300);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        TextField textField = new TextField("Enter name");
                        TextField textField1 = new TextField("Enter address");
                        TextField textField2 = new TextField("Enter credit card number");
                        TextField textField3 = new TextField("Enter expire month");
                        TextField textField4 = new TextField("Enter expire year");
                        TextField textField5 = new TextField("Enter CCV");
                        frame.add(textField);
                        frame.add(textField1);
                        frame.add(textField2);
                        frame.add(textField3);
                        frame.add(textField4);
                        frame.add(textField5);
                        JButton button2 = new JButton("Apply");
                        frame.add(button2);

                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Individual individual = new Individual(textField.getText(), textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()));
                                if (distributor.addSubscriber(individual)) {
                                    String message = "An individual subscriber is successfully created and added to list!";
                                    message += individual.getBillingInformation();
                                    JOptionPane.showMessageDialog(null, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
                                    frame.dispose();
                                    return;
                                } else {
                                    JOptionPane.showMessageDialog(null, "An error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
                                    frame.dispose();
                                    return;
                                }
                            }
                        });


                    }
                });
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        JFrame frame = new JFrame();
                        frame.setTitle("Create Corporation");
                        frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                        frame.getContentPane().setBackground(new Color(118, 217, 213));
                        frame.setLayout(new GridLayout(9, 1, 40, 10));
                        frame.setSize(400, 300);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        TextField textField = new TextField("Enter name");
                        TextField textField1 = new TextField("Enter address");
                        TextField textField2 = new TextField("Enter bank code");
                        TextField textField3 = new TextField("Enter bank name");
                        TextField textField4 = new TextField("Enter issue day");
                        TextField textField5 = new TextField("Enter issue month");
                        TextField textField6 = new TextField("Enter issue year");
                        TextField textField7 = new TextField("Enter account number");
                        JButton button3 = new JButton("Create Corporation");
                        frame.add(textField);
                        frame.add(textField1);
                        frame.add(textField2);
                        frame.add(textField3);
                        frame.add(textField4);
                        frame.add(textField5);
                        frame.add(textField6);
                        frame.add(textField7);
                        frame.add(button3);
                        button3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Corporation corporation = new Corporation(textField.getText(), textField1.getText(), Integer.parseInt(textField2.getText()), textField3.getText(), Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()), Integer.parseInt(textField7.getText()));
                                if (distributor.addSubscriber(corporation)) {
                                    String message = "An corporation subscriber is successfully created and added to list!";
                                    message += corporation.getBillingInformation();
                                    JOptionPane.showMessageDialog(null, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
                                    frame.dispose();
                                    return;
                                } else {
                                    JOptionPane.showMessageDialog(null, "An error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
                                    frame.dispose();
                                    return;
                                }
                            }
                        });

                    }
                });
            }
        });
        searchSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.setTitle("Search");
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(2, 1, 40, 10));
                frame.setSize(250, 125);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter name");
                frame.add(textField);
                JButton button = new JButton("Search Subscriber");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Subscriber subscriber = distributor.searchSubscriber(textField.getText());
                        if (subscriber != null) {
                            JOptionPane.showMessageDialog(null, subscriber.getBillingInformation(), "Subscriber Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Subscriber is not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        frame.dispose();
                        return;
                    }
                });
            }
        });
        // önce subscription oluştur
        // daha sonra onu listeye ekle
        createSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Date & Payment Information");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(7, 1, 40, 10));
                JLabel label = new JLabel("\tDate Information");
                frame.setSize(350, 300);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.add(label);
                TextField textField = new TextField("Enter start month");
                TextField textField2 = new TextField("Enter start year");
                frame.add(textField);
                frame.add(textField2);
                JLabel label1 = new JLabel("\tPayment Information");
                frame.add(label1);
                TextField textField3 = new TextField("Enter discount ratio");
                TextField textField4 = new TextField("Enter received payment");
                frame.add(textField3);
                frame.add(textField4);
                JButton button = new JButton("Confirm");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        DateInfo dateInfo = new DateInfo(Integer.parseInt(textField.getText()), Integer.parseInt(textField2.getText()));
                        PaymentInfo paymentInfo = new PaymentInfo(Double.parseDouble(textField3.getText()), Double.parseDouble(textField4.getText()));
                        frame.dispose();
                        JFrame frame1 = new JFrame();
                        frame1.setIconImage(new ImageIcon("adsiz2.png").getImage());
                        frame1.getContentPane().setBackground(new Color(118, 217, 213));
                        frame1.setLayout(new GridLayout(4, 1, 40, 10));
                        frame1.setSize(200, 200);
                        frame1.setVisible(true);
                        frame1.setLocationRelativeTo(null);
                        frame1.setTitle("Create Subscription");
                        TextField textField5 = new TextField("Enter copies");
                        TextField textField6 = new TextField("Enter name of the subscriber");
                        TextField textField7 = new TextField("Enter ISSN of the journal");
                        frame1.add(textField5);
                        frame1.add(textField6);
                        frame1.add(textField7);
                        JButton button1 = new JButton("Create Subscription");
                        frame1.add(button1);
                        button1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame1.dispose();
                                String name = textField6.getText();
                                String ISSN = textField7.getText();
                                Subscriber subscriber = distributor.searchSubscriber(name);
                                if (subscriber == null) {
                                    JOptionPane.showMessageDialog(null, name + " named subscriber is not found!", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                Journal journal = distributor.searchJournal(ISSN);
                                if (journal == null) {
                                    JOptionPane.showMessageDialog(null, "Journal #" + ISSN + " is not found!", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                Subscription subscription = new Subscription(dateInfo, paymentInfo, Integer.parseInt(textField5.getText()), subscriber, journal);
                                if (distributor.addSubscription(ISSN, subscriber, subscription)) {
                                    JOptionPane.showMessageDialog(null, "A new subscription is successfully created and added to the list!\n" + subscription.toString(), "Create Subscription", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog(null, "An error occurred when adding new subscription!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                return;
                            }
                        });

                    }
                });

            }
        });
        listAllSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Select Month & Year");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(4, 1, 30, 20));
                frame.setSize(200, 200);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                JLabel label = new JLabel("Select month & year");
                frame.add(label);
                TextField textField = new TextField("Enter month");
                TextField textField1 = new TextField("Enter year");
                frame.add(textField);
                frame.add(textField1);
                JButton button = new JButton("Confirm");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        String string = distributor.listAllSendingOrders(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText()));
                        JOptionPane.showMessageDialog(null, "                                                                                                  Order List\n" + string, "List of All Sending Orders", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                });
            }
        });
        listSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Select Search Type");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(2, 1, 30, 20));
                frame.setSize(300, 150);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                JButton button = new JButton("List for Subscriber Name");
                JButton button1 = new JButton("List for Journal ISSN");
                frame.add(button);
                frame.add(button1);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        frame.setTitle("Enter Name");
                        frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                        frame.getContentPane().setBackground(new Color(118, 217, 213));
                        frame.setLayout(new GridLayout(2, 1, 10, 10));
                        frame.setSize(300, 100);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        TextField textField = new TextField("Enter subscriber name");
                        frame.add(textField);
                        JButton jButton = new JButton("Search");
                        frame.add(jButton);
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = textField.getText();
                                JOptionPane.showMessageDialog(null, distributor.listSubscriptions(name), "Subscription List", JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                return;
                            }
                        });

                    }
                });
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        frame.setTitle("Enter Journal ISSN");
                        frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                        frame.getContentPane().setBackground(new Color(118, 217, 213));
                        frame.setLayout(new GridLayout(2, 1, 10, 10));
                        frame.setSize(300, 100);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        TextField textField = new TextField("Enter journal ISSN");
                        frame.add(textField);
                        JButton jButton = new JButton("Search");
                        frame.add(jButton);
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String ISSN = textField.getText();
                                JOptionPane.showMessageDialog(null, distributor.listSubscriptionsForJournal(ISSN), "Subscription List", JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                return;
                            }
                        });
                    }
                });
            }
        });
        listSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("List Journal Orders");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(4, 1, 30, 18));
                frame.setSize(250, 250);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter journal ISSN");
                TextField textField1 = new TextField("Enter month");
                TextField textField2 = new TextField("Enter year");
                frame.add(textField);
                frame.add(textField1);
                frame.add(textField2);
                JButton button = new JButton("List");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (distributor.searchJournal(textField.getText()) == null) {
                            JOptionPane.showMessageDialog(null, "Journal #" + textField.getText() + " cannot be found in the list!", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            String message = distributor.listSendingOrders(textField.getText(), Integer.parseInt(textField1.getText()), Integer.parseInt(textField2.getText()));
                            JOptionPane.showMessageDialog(null, message, "List Of Journal #" + textField.getText(), JOptionPane.INFORMATION_MESSAGE);

                        }
                        frame.dispose();
                        return;
                    }
                });
            }
        });
        loadStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Load State From File");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(2, 1, 30, 15));
                frame.setSize(300, 120);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter filename");
                frame.add(textField);
                JButton button = new JButton("Load");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String message = distributor.loadState(textField.getText());
                        JOptionPane.showMessageDialog(null, message, "Load State", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                });
            }
        });
        saveStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Save State To File");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(2, 1, 30, 15));
                frame.setSize(300, 120);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter filename");
                frame.add(textField);
                JButton button = new JButton("Save");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (distributor.saveState(textField.getText())) {
                            JOptionPane.showMessageDialog(null, "Current state is successfully saved to " + textField.getText() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Current state cannot be saved!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        frame.dispose();
                        return;
                    }
                });
            }
        });
        listIncompletePaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!distributor.listIncompletePayments().isEmpty()) {

                    String message = "                          List Of Incomplete Payments\n                      *************************************\n";
                    message += distributor.listIncompletePayments();
                    JOptionPane.showMessageDialog(null, message, "List Of Incomplete Payments", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "There is zero instance of incomplete payments!", "List Of Incomplete Payments", JOptionPane.INFORMATION_MESSAGE);
                }
                return;
            }
        });
        reportStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Select Month & Year");
                frame1.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame1.getContentPane().setBackground(new Color(118, 217, 213));
                frame1.setLayout(new GridLayout(3, 1, 30, 15));
                frame1.setSize(300, 150);
                frame1.setVisible(true);
                frame1.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter month");
                TextField textField1 = new TextField("Enter year");
                frame1.add(textField);
                frame1.add(textField1);
                JButton button = new JButton("Confirm");
                frame1.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        int month = Integer.parseInt(textField.getText());
                        int year = Integer.parseInt(textField1.getText());
                        distributor.report(month, year);


                    }
                });
            }
        });
        AcceptPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Find Subscription");
                frame.setIconImage(new ImageIcon("adsiz2.png").getImage());
                frame.getContentPane().setBackground(new Color(118, 217, 213));
                frame.setLayout(new GridLayout(4, 1, 30, 18));
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                TextField textField = new TextField("Enter name of subscriber");
                TextField textField1 = new TextField("Enter journal ISSN");
                TextField textField2 = new TextField("Enter amount");
                JButton button = new JButton("Confirm");
                frame.add(textField);
                frame.add(textField1);
                frame.add(textField2);
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = textField.getText();
                        String ISSN = textField1.getText();
                        double amount = Double.parseDouble(textField2.getText());
                        Journal journal = distributor.searchJournal(ISSN);
                        if (journal == null) {
                            JOptionPane.showMessageDialog(null, "Journal #" + ISSN + " cannot be found!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.dispose();
                            return;
                        }
                        Subscriber subscriber = distributor.searchSubscriber(name);
                        if (subscriber == null) {
                            JOptionPane.showMessageDialog(null, "Subscriber " + name + " cannot be found!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.dispose();
                            return;
                        }
                        for (Subscription s : journal.getSubscriptions()) {
                            if (s.getSubscriber() == subscriber) {
                                s.getPayment().increasePayment(amount);
                                JOptionPane.showMessageDialog(null, "Added amount : " + amount + "$ Total received payment : " + s.getPayment().getReceivedPayment() + "$", "Payment accepted", JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                return;
                            }

                        }
                        JOptionPane.showMessageDialog(null, "An error occurred while payment! Check ISSN & name!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.dispose();
                        return;
                    }
                });

            }
        });
    }

    public static void main(String[] args) {
        Distributor distributor = new Distributor();
/*
*
        Individual individual = new Individual("Bora Ilci", "Bursa/Turkey", "011035", 9, 26, 635);
        Individual individual1 = new Individual("Novak Djokovic", "Belgrad/Serbia", "011050", 3, 24, 504);
        Individual individual2 = new Individual("Sebastian Vettel", "Heppenheim/Germany", "011092", 5, 28, 186);
        Corporation corporation = new Corporation("From Software", "Tokyo/Japan", 9645789, "Development Bank of Japan", 4, 10, 2023, 21011001);
        Journal journal = new Journal("Sports", "1001", 12, 6.49);
        Journal journal1 = new Journal("Formula 1", "1002", 6, 4.99);
        Journal journal2 = new Journal("Tennis", "1003", 12, 5.49);
        DateInfo dateInfo = new DateInfo(1, 2023); // bora
        DateInfo dateInfo1 = new DateInfo(3, 2023); // novak
        DateInfo dateInfo2 = new DateInfo(5, 2023); //seb
        DateInfo dateInfo3 = new DateInfo(4, 2023); // ffrom
        PaymentInfo paymentInfo = new PaymentInfo(0, 50);
        PaymentInfo paymentInfo1 = new PaymentInfo(5, 30);
        PaymentInfo paymentInfo2 = new PaymentInfo(5, 0);
        PaymentInfo paymentInfo3 = new PaymentInfo(10, 80);
        distributor.addJournal(journal);
        distributor.addJournal(journal1);
        distributor.addJournal(journal2);
        distributor.addSubscriber(individual);
        distributor.addSubscriber(individual1);
        distributor.addSubscriber(individual2);
        distributor.addSubscriber(corporation);
        Subscription subscription = new Subscription(dateInfo, paymentInfo, 0, individual, journal); // bora
        Subscription subscription1 = new Subscription(dateInfo1, paymentInfo1, 0, individual1, journal2); // novak
        Subscription subscription2 = new Subscription(dateInfo2, paymentInfo2, 0, individual2, journal1); // seb
        Subscription subscription3 = new Subscription(dateInfo3, paymentInfo3, 0, corporation, journal); // from soft
        distributor.addSubscription(journal.getIssn(), individual, subscription);
        distributor.addSubscription(journal2.getIssn(), individual1, subscription1);
        distributor.addSubscription(journal1.getIssn(), individual2, subscription2);
        distributor.addSubscription(journal.getIssn(), corporation, subscription3);*/
        MainGUI gui = new MainGUI(distributor);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(10, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setAlignmentX(0.0f);
        panel1.setAlignmentY(0.0f);
        panel1.setBackground(new Color(-8988203));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-8988203));
        Font panel2Font = this.$$$getFont$$$(null, -1, -1, panel2.getFont());
        if (panel2Font != null) panel2.setFont(panel2Font);
        panel2.setForeground(new Color(-8988203));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-8988203));
        Font label1Font = this.$$$getFont$$$("Bookshelf Symbol 7", -1, 40, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("Socrates Journal ");
        panel2.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-8988203));
        Font label2Font = this.$$$getFont$$$("Bookshelf Symbol 7", -1, 40, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("Distribution System ");
        panel2.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setIcon(new ImageIcon(getClass().getResource("/adsiz2.png")));
        label3.setText("");
        panel2.add(label3, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-16777216));
        Font label4Font = this.$$$getFont$$$("Bookshelf Symbol 7", Font.BOLD, 20, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-16777216));
        label4.setText("Subscription Operations");
        panel1.add(label4, new GridConstraints(5, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-8988203));
        Font label5Font = this.$$$getFont$$$("Bookshelf Symbol 7", Font.BOLD, 20, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-16777216));
        label5.setText("Subscriber Operations");
        panel1.add(label5, new GridConstraints(3, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setBackground(new Color(-8988203));
        Font label6Font = this.$$$getFont$$$("Bookshelf Symbol 7", Font.BOLD, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-16777216));
        label6.setText("Journal Operations");
        panel1.add(label6, new GridConstraints(1, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AcceptPaymentButton = new JButton();
        AcceptPaymentButton.setText("Accept Payment");
        panel1.add(AcceptPaymentButton, new GridConstraints(6, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-16777216));
        Font label7Font = this.$$$getFont$$$("Bookshelf Symbol 7", Font.BOLD, 20, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-16777216));
        label7.setText("Order Operations");
        panel1.add(label7, new GridConstraints(7, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listAllSendingOrdersButton = new JButton();
        listAllSendingOrdersButton.setText("List All Sending Orders");
        panel1.add(listAllSendingOrdersButton, new GridConstraints(8, 1, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createJournalButton = new JButton();
        createJournalButton.setRequestFocusEnabled(false);
        createJournalButton.setText("Create Journal");
        panel1.add(createJournalButton, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchJournalButton = new JButton();
        searchJournalButton.setText("Search Journal");
        panel1.add(searchJournalButton, new GridConstraints(2, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createSubscriberButton = new JButton();
        createSubscriberButton.setText("Create Subscriber");
        panel1.add(createSubscriberButton, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchSubscriberButton = new JButton();
        searchSubscriberButton.setText("Search Subscriber");
        panel1.add(searchSubscriberButton, new GridConstraints(4, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reportStateButton = new JButton();
        reportStateButton.setText("Report State");
        panel1.add(reportStateButton, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createSubscriptionButton = new JButton();
        createSubscriptionButton.setText("Create Subscription");
        panel1.add(createSubscriptionButton, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listSubscriptionsButton = new JButton();
        listSubscriptionsButton.setText("List Subscriptions");
        panel1.add(listSubscriptionsButton, new GridConstraints(6, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listIncompletePaymentsButton = new JButton();
        listIncompletePaymentsButton.setText("List Incomplete Payments");
        panel1.add(listIncompletePaymentsButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listSendingOrdersButton = new JButton();
        listSendingOrdersButton.setText("List Sending Orders");
        panel1.add(listSendingOrdersButton, new GridConstraints(8, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveStateButton = new JButton();
        saveStateButton.setText("Save State");
        panel1.add(saveStateButton, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadStateButton = new JButton();
        loadStateButton.setText("Load State");
        panel1.add(loadStateButton, new GridConstraints(9, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
