package exchangeOfficePAO.models;

public class Cashier extends Employee{
    static final private double baseSalary = 2000;
    static final private double bonusPerYear = 100;

    private int deskNo;
    public Cashier(String firstName, String lastName, int experience, int deskNo) {
        super(firstName, lastName, experience);
        this.deskNo = deskNo;
        this.calculateSalary();
    }

    public int getDeskNo() {
        return deskNo;
    }

    @Override
    protected void calculateSalary() {
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
                ", experience=" + experience +
                "}\n";
    }
}
