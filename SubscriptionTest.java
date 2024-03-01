import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubscriptionTest {
    Subscription subscription;

    @Test
    void testSubscription() {
        DateInfo dateInfo = new DateInfo(10, 2021);
        Subscriber subscriber = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        Journal journal = new Journal("Tennis", "1003", 6, 7.49);
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        subscription = new Subscription(dateInfo, paymentInfo, 2, subscriber, journal);
        Assertions.assertEquals(paymentInfo, subscription.getPayment());
        Assertions.assertEquals(dateInfo, subscription.getDates());
        Assertions.assertEquals(2, subscription.getCopies());
        Assertions.assertEquals(subscriber, subscription.getSubscriber());
        Assertions.assertEquals(journal, subscription.getJournal());
    }


    @Test
    void testAcceptPayment() {
        DateInfo dateInfo = new DateInfo(10, 2021);
        Subscriber subscriber = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        Journal journal = new Journal("Tennis", "1003", 6, 7.49);
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        subscription = new Subscription(dateInfo, paymentInfo, 2, subscriber, journal);
        subscription.acceptPayment(100);
        Assertions.assertEquals(subscription.getPayment().getReceivedPayment(), 100);
        subscription.acceptPayment(200);
        Assertions.assertEquals(subscription.getPayment().getReceivedPayment(), 300);
    }

    @Test
    void testCanSend() {
        DateInfo dateInfo = new DateInfo(10, 2021);
        Subscriber subscriber = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        Journal journal = new Journal("Tennis", "1003", 6, 7.49);
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        subscription = new Subscription(dateInfo, paymentInfo, 2, subscriber, journal);
        Assertions.assertFalse(subscription.canSend(12));
        subscription.acceptPayment(30);
        Assertions.assertTrue(subscription.canSend(12));
    }


}