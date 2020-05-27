package exchangeOfficePAO.models;

import java.time.LocalDate;

public class Guardian extends Employee {
    static final private double baseSalary = 1800;
    public Guardian(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
        this.calculateSalary();
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id="+id+
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "}\n";
    }
}
