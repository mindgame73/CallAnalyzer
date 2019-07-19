package Model;

public class RowService {
    private long phone;
    private long code;
    private String serviceName;
    private float amount;
    private float cost;



    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "RowService{" +
                "phone=" + phone +
                ", code=" + code +
                ", serviceName='" + serviceName + '\'' +
                ", amount=" + amount +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowService that = (RowService) o;

        if (phone != that.phone) return false;
        if (code != that.code) return false;
        if (Float.compare(that.amount, amount) != 0) return false;
        if (Float.compare(that.cost, cost) != 0) return false;
        return serviceName != null ? serviceName.equals(that.serviceName) : that.serviceName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (phone ^ (phone >>> 32));
        result = 31 * result + (int) (code ^ (code >>> 32));
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        return result;
    }
}
