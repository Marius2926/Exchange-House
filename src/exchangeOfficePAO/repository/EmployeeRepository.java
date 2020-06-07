package exchangeOfficePAO.repository;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public EmployeeRepository() {
    }

    public void addEmployee(Employee e){
        DatabaseConnection.getInstance().insertEmployee(e);
    }

    public List<Employee> getEmployeeList(){
        return DatabaseConnection.getInstance().getEmployees();
    }

    public Employee getEmployeeAfterId(int id){
        for(Employee employee : this.getEmployeeList()){
            if(employee.getId() == id)
                return employee;
        }
        return null;
    }
}
