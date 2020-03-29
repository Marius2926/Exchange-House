package exchangeOfficePAO.models;

public class Guardian extends Employee {
    static final private double baseSalary = 1800;
    public Guardian(String firstName, String lastName, int experience) {
        super(firstName, lastName, experience);
        this.calculateSalary();
    }

    @Override
    protected void calculateSalary() {
        this.salary = baseSalary;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id="+id+
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                "}\n";
    }
}
