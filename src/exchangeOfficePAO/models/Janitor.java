package exchangeOfficePAO.models;

import java.time.LocalDate;
import java.time.Period;

public class Janitor extends Employee{
    static final private double baseSalary = 1600;
    static final private double bonusPerYear = 60;
    public Janitor(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
        this.calculateSalary();
    }

    @Override
    public int getType() {
        return 2;
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
        return "Janitor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "}\n";
    }
}
