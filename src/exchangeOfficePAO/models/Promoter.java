package exchangeOfficePAO.models;

public class Promoter extends Employee{
    static final private double baseSalary = 1200;
    public Promoter(String firstName, String lastName, int experience) {
        super(firstName, lastName, experience);
        this.calculateSalary();
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
                ", experience=" + experience +
                "}\n";
    }
}
