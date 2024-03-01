import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentInfoTest {


    @Test
    void testPaymentInfo() {
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        Assertions.assertEquals(0, paymentInfo.getReceivedPayment());
        Assertions.assertEquals(0.1, paymentInfo.getDiscountRatio());
        paymentInfo.setReceivedPayment(100);
        Assertions.assertEquals(paymentInfo.getReceivedPayment(), 100);
    }

    @Test
    void testIncreasePayment() {
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 0);
        double amount = paymentInfo.getReceivedPayment() + 100;
        paymentInfo.increasePayment(amount);
        Assertions.assertEquals(amount, paymentInfo.getReceivedPayment());
        amount += 120;
        paymentInfo.increasePayment(amount);
        Assertions.assertEquals(amount + 100, paymentInfo.getReceivedPayment());
    }

    @Test
    public void testToString() {
        PaymentInfo paymentInfo = new PaymentInfo(0.15, 120.0);

        String expectedString = "Discount Ratio : 0.15\nReceived Payment : 120.0\n";
        Assertions.assertEquals(expectedString, paymentInfo.toString());
    }
}