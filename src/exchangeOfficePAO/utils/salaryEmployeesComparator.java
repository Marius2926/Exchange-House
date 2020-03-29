package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Employee;

import java.util.Comparator;

public class salaryEmployeesComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        Employee o1 = (Employee) o;
        Employee o2 = (Employee) t1;
        if(o1.getSalary() > o2.getSalary())
            return -1;
        else{
            if(o1.getSalary() < o2.getSalary())
                return 1;
            return 0;
        }
    }
}
