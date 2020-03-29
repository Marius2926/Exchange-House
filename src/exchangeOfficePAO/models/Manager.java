package exchangeOfficePAO.models;

public class Manager extends Employee{
    static final private double baseSalary = 2800;
    static final private double bonusPerYear = 100;
    public Manager(String firstName, String lastName, int experience) {
        super(firstName, lastName, experience);
        this.calculateSalary();
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary + experience * bonusPerYear;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                "\n";
    }
}
