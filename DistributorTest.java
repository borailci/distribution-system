import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class DistributorTest {

    @Test
    void testAddAndSearchJournal() {
        Distributor d = new Distributor();
        Journal journal = new Journal("Sports", "1001", 2, 10);
        d.addJournal(journal);
        assertSame(journal, d.searchJournal("1001"));
    }

    @Test
    void testAddAndSearchSubscriber() {
        Distributor d = new Distributor();
        Individual individual = new Individual("Bora", "TR", "123456", 11, 24, 123);
        d.addSubscriber(individual);
        assertSame(individual, d.searchSubscriber("Bora"));
    }

    @Test
    void testAddSubscription() {
        Distributor d = new Distributor();
        Individual individual = new Individual("Bora", "TR", "123456", 11, 24, 123);
        DateInfo dateInfo = new DateInfo(2, 2022);
        Journal journal = new Journal("Sports", "1001", 2, 10);
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        Subscription subscription = new Subscription(dateInfo, paymentInfo, 2, individual, journal);
        d.addJournal(journal);
        Assertions.assertFalse(d.addSubscription("1000", individual, subscription));
        Assertions.assertFalse(d.addSubscription("1001", null, subscription));
        Assertions.assertFalse(d.addSubscription("1001", individual, null));
        Assertions.assertTrue(d.addSubscription("1001", individual, subscription));

    }


}