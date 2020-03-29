package exchangeOfficePAO.models;

public class Supervisor extends Employee {
    static final private double baseSalary = 2350;
    static final private double bonusPerYear = 75;
    public Supervisor(String firstName, String lastName, int experience) {
        super(firstName, lastName, experience);
        this.calculateSalary();
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary + this.experience * bonusPerYear;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                "\n";
    }
}
