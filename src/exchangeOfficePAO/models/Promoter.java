package exchangeOfficePAO.models;

import java.time.LocalDate;

public class Promoter extends Employee{
    static final private double baseSalary = 1200;
    public Promoter(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
        this.calculateSalary();
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary;
    }

    @Override
    public String toString() {
        return "Promoter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "}\n";
    }
}
