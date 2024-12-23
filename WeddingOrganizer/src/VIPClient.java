public class VIPClient extends Client {
    private double discountRate; // Atribut khusus untuk VIP Client

    // Constructor
    public VIPClient(int id, String name, String contact, String email, double discountRate) {
        super(id, name, contact, email); // Memanggil constructor superclass Client
        this.discountRate = discountRate;
    }

    // Getter dan Setter untuk discountRate
    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    // Override toString() untuk menampilkan informasi tambahan
    @Override
    public String toString() {
        return super.toString() + ", DiscountRate=" + discountRate + "]";
    }
}
