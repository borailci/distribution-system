import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateInfoTest {
    @Test
    void testDateInfo() {
        DateInfo dateInfo = new DateInfo(4, 2022);
        Assertions.assertEquals(4, dateInfo.getStartMonth());
        Assertions.assertEquals(3, dateInfo.getEndMonth());
        Assertions.assertEquals(2022, dateInfo.getStartYear());
        dateInfo.setStartMonth(8);
        Assertions.assertEquals(8, dateInfo.getStartMonth());
        Assertions.assertEquals(7, dateInfo.getEndMonth());
        dateInfo.setStartYear(2025);
        Assertions.assertEquals(2025, dateInfo.getStartYear());
        String expected = "Start Month : 8\nEnd Month : 7\nStart Year : 2025\n";
        Assertions.assertEquals(expected, dateInfo.toString());
    }


}