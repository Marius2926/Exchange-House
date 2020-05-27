package exchangeOfficePAO.models;

import java.time.LocalDate;
import java.time.Period;

public class Manager extends Employee{
    static final private double baseSalary = 2800;
    static final private double bonusPerYear = 100;

    public Manager(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
        this.calculateSalary();
    }

    @Override
    public int getType() {
        return 3;
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
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "\n";
    }
}
