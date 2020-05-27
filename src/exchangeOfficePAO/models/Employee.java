package exchangeOfficePAO.models;

import java.time.LocalDate;

public abstract class Employee {
    private static int numberEmployees = 0;
    protected int id;
    protected String firstName, lastName;
    protected double salary;
    protected LocalDate hireDate;
    abstract protected void calculateSalary();

    public Employee(String firstName, String lastName, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getHireDate() { return hireDate; }

    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public abstract int getType();
}
