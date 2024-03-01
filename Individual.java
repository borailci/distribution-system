public class Individual extends Subscriber {

    private String creditCardNr;
    private int expireMonth, expireYear, CCV;

    public Individual(String name, String address, String creditCardNr, int expireMonth, int expireYear, int CCV) {
        super(name, address);
        this.creditCardNr = creditCardNr;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CCV = CCV;
    }

    public String getCreditCardNr() {
        return creditCardNr;
    }

    public void setCreditCardNr(String creditCardNr) {
        this.creditCardNr = creditCardNr;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public int getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    public int getCCV() {
        return CCV;
    }

    public void setCCV(int CCV) {
        this.CCV = CCV;
    }

    public String getBillingInformation() {
        String string;
        string = "\nName : " + getName() + "\nAddress : " + getAddress() + "\nBilling Information\n******************";
        string += "\nCredit Card Number : " + creditCardNr;
        string += "\nExpire Month : " + expireMonth;
        string += "\nExpire Year : " + expireYear;
        string += "\nCCV : " + CCV;
        return string;
    }
}
