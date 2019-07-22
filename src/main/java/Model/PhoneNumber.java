package Model;

public class PhoneNumber {
    private long phone;
    private String division;
    private String employeeName;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phone=" + phone +
                ", division='" + division + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (phone != that.phone) return false;
        if (division != null ? !division.equals(that.division) : that.division != null) return false;
        return employeeName != null ? employeeName.equals(that.employeeName) : that.employeeName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (phone ^ (phone >>> 32));
        result = 31 * result + (division != null ? division.hashCode() : 0);
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        return result;
    }
}
