import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CorporationTest {

    @Test
    void testCorporation() {
        Corporation corporation = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        Assertions.assertEquals(corporation.getName(), "From Software");
        Assertions.assertEquals(corporation.getAddress(), "South Korea");
        Assertions.assertEquals(corporation.getBankCode(), 123);
        Assertions.assertEquals(corporation.getBankName(), "Akbank");
        Assertions.assertEquals(corporation.getAccountNumber(), 123456);
        Assertions.assertEquals(corporation.getIssueDay(), 4);
        Assertions.assertEquals(corporation.getIssueMonth(), 10);
        Assertions.assertEquals(corporation.getIssueYear(), 2023);
    }

    @Test
    void getBillingInformation() {
        Corporation corporation = new Corporation("From Software", "South Korea", 123, "Akbank", 4, 10, 2023, 123456);
        String string = corporation.getBillingInformation();
        assertTrue(string.contains("From Software"));
        assertTrue(string.contains("South Korea"));
        assertTrue(string.contains("Bank Name & Code : Akbank & 123"));
        assertTrue(string.contains("Issue Day/Month/Year : 4/10/2023"));
        assertTrue(string.contains("Account Number : 123456"));
        System.out.println(string);
    }
}