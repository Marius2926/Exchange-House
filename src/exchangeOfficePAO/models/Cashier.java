package exchangeOfficePAO.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Cashier extends Employee{
    static final private double baseSalary = 2000;
    static final private double bonusPerYear = 100;

    @Override
    public int getType() {
        return 0;
    }

    private int deskNo;
    public Cashier(String firstName, String lastName, LocalDate hireDate, int deskNo) {
        super(firstName, lastName, hireDate);
        this.deskNo = deskNo;
        this.calculateSalary();
    }

    public int getDeskNo() {
        return deskNo;
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
        return "Cashier{" +
                "id="+id+
                ", deskNo=" + deskNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate.toString() +
                "}\n";
    }
}
