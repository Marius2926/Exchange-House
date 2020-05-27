package exchangeOfficePAO.models;

import java.time.LocalDate;
import java.time.Period;

public class Supervisor extends Employee {
    static final private double baseSalary = 2350;
    static final private double bonusPerYear = 75;
    public Supervisor(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
        this.calculateSalary();
    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    protected void calculateSalary() {
        LocalDate actualDate = LocalDate.now();
        Period period = Period.between(actualDate, hireDate);
        int experience = period.getYears();
        this.salary = baseSalary + experience * bonusPerYear;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "\n";
    }
}
