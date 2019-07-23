package Model;

public class Employee {
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

        Employee employee = (Employee) o;

        if (phone != employee.phone) return false;
        if (division != null ? !division.equals(employee.division) : employee.division != null) return false;
        return employeeName != null ? employeeName.equals(employee.employeeName) : employee.employeeName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (phone ^ (phone >>> 32));
        result = 31 * result + (division != null ? division.hashCode() : 0);
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        return result;
    }
}
