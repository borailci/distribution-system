import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IndividualTest {

    @Test
    void testIndividual() {
        Individual individual = new Individual("Bora", "Turkey", "159753123", 1, 25, 123);
        Assertions.assertEquals("Bora", individual.getName());
        Assertions.assertEquals("Turkey", individual.getAddress());
        Assertions.assertEquals("159753123", individual.getCreditCardNr());
        Assertions.assertEquals(1, individual.getExpireMonth());
        Assertions.assertEquals(25, individual.getExpireYear());
        Assertions.assertEquals(123, individual.getCCV());
        individual.setName("Mehmet");
        individual.setCCV(300);
        individual.setCreditCardNr("123456");
        individual.setExpireMonth(10);
        individual.setExpireYear(2023);
        individual.setAddress("Germany");
        Assertions.assertEquals(individual.getName(), "Mehmet");
        Assertions.assertEquals(individual.getCCV(), 300);
        Assertions.assertEquals(individual.getCreditCardNr(), "123456");
        Assertions.assertEquals(individual.getExpireMonth(), 10);
        Assertions.assertEquals(individual.getExpireYear(), 2023);
        Assertions.assertEquals(individual.getAddress(), "Germany");
    }

    @Test
    void testGetBillingInformation() {
        Individual individual = new Individual("Bora", "Turkey", "159753123", 1, 25, 123);
        String string = individual.getBillingInformation();
        assertTrue(string.contains("Bora"));
        assertTrue(string.contains("Turkey"));
        assertTrue(string.contains("Credit Card Number : 159753123"));
        assertTrue(string.contains("CCV : 123"));
        assertTrue(string.contains("Expire Month : 1"));
        assertTrue(string.contains("Expire Year : 25"));
    }
}