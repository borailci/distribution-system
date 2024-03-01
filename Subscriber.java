import java.io.Serializable;

public abstract class Subscriber implements Serializable {
    private String name, address;

    public Subscriber(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract String getBillingInformation();

    @Override
    public String toString() {
        return "Subscriber Name : " + name + '\n' + "Subscriber Address : " + address + '\n';
    }
}
