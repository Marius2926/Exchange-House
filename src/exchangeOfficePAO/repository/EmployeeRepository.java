package exchangeOfficePAO.repository;

import exchangeOfficePAO.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    List<Employee> employeeList;

    public EmployeeRepository() {
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee e){
        employeeList.add(e);
    }

    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    public Employee getEmployeeAfterId(int id){
        for(Employee employee : employeeList){
            if(employee.getId() == id)
                return employee;
        }
        return null;
    }
}
