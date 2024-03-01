import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JournalTest {
    @Test
    void testJournalConstructor() {
        Journal journal = new Journal("Football", "1001", 12, 5.99);
        Assertions.assertEquals("Football", journal.getName());
        Assertions.assertEquals("1001", journal.getIssn());
        Assertions.assertEquals(12, journal.getFrequency());
        Assertions.assertEquals(5.99, journal.getIssuePrice());
        assertNotNull(journal.getSubscriptions());
    }

    @Test
    void testAddSubscription() {
        Journal journal = new Journal("Football", "1001", 12, 5.99);
        DateInfo dateInfo = new DateInfo(3, 2024);
        Subscriber sub1 = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        Subscription s1 = new Subscription(dateInfo, paymentInfo, 1, sub1, journal);
        journal.addSubscription(s1);
        Assertions.assertTrue(journal.addSubscription(s1));
        Assertions.assertTrue(journal.getSubscriptions().contains(s1));
    }

}