package exchangeOfficePAO.models;

public class Janitor extends Employee{
    static final private double baseSalary = 1600;
    static final private double bonusPerYear = 60;
    public Janitor(String firstName, String lastName, int experience) {
        super(firstName, lastName, experience);
        this.calculateSalary();
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary + experience * bonusPerYear;
    }

    @Override
    public String toString() {
        return "Janitor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                "}\n";
    }
}
