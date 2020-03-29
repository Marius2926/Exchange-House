package exchangeOfficePAO.models;

public abstract class Employee {
    private static int numberEmployees = 0;
    protected int id;
    protected String firstName, lastName;
    protected double salary;
    protected int experience;
    abstract protected void calculateSalary();

    public Employee(String firstName, String lastName, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.id = ++numberEmployees;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
