import java.io.Serializable;

public class DateInfo implements Serializable {
    private int startMonth, endMonth, startYear;

    public DateInfo(int startMonth, int startYear) {
        this.startMonth = startMonth;
        this.startYear = startYear;
        endMonth = startMonth == 1 ? 12 : startMonth - 1;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
        endMonth = startMonth == 1 ? 12 : startMonth - 1;
    }

    public int getEndMonth() {
        return endMonth;
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Override
    public String toString() {
        return "Start Month : " + startMonth + '\n' + "End Month : " + endMonth + '\n' + "Start Year : " + startYear + '\n';
    }
}