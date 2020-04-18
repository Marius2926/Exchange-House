package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Employee;
import exchangeOfficePAO.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository  = new EmployeeRepository();
    }

    public void addEmployee(Employee e){
        employeeRepository.addEmployee(e);
    }

    public List<Employee> getEmployeeRepository(){
        return employeeRepository.getEmployeeList();
    }

    Employee getEmployeeAfterId(int id) {
        return employeeRepository.getEmployeeAfterId(id);
    }

    @Override
    public String toString() {
        String result = "Employees { \n";
        for(Employee employee : employeeRepository.getEmployeeList()){
            result += employee.toString() + "\n";
        }
        result += "\n}";
        return result;
    }
}
