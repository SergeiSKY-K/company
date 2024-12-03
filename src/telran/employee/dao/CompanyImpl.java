package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompanyImpl implements Company {
    private List<Employee> employees;
    private int capacity;

    public CompanyImpl(int capacity) {
        this.capacity = capacity;
       employees = new ArrayList<>();
    }

    @Override //O(n)
    public boolean addEmployee(Employee employee) {
        if(employee == null || capacity == employees.size() || findEmployee(employee.getId()) != null) {
            return false;
        }
        return employees.add(employee);
    }

    @Override //O(n)
    public Employee removeEmployee(int id) {
        Employee victim = findEmployee(id);
        employees.remove(victim);
        return victim;
    }

    @Override //O(n)
    public Employee findEmployee(int id) {
        for (Employee employee: employees){
            if (employee.getId()==id){
                return employee;
            }
        }
        return null;
    }

    @Override //O(1)
    public int quantity() {
        return employees.size();
    }

    @Override //O(n)
    public double totalSalary() {
        double sum = 0;
        for (Employee employee: employees) {
            sum += employee.calcSalary();
        }
        return sum;
    }


    @Override //O(n)
    public double totalSales() {
        double sum = 0;
        for (Employee employee :employees) {
            if (employee instanceof SalesManager sm) {
                sum += sm.getSalesValue();
            }
        }
        return sum;
    }

    @Override //O(n)
    public void printEmployees() {
        System.out.println("=======================");
        for (Employee employee:employees) {
            System.out.println(employees);
        }
        System.out.println("=======================");
    }

    @Override //O(n)
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
       return findEmployeesByPredicate(e -> e.getHours()>hours);
    }

    @Override //O(n)
    public Employee[] findEmployesSalaryBetween(int minSalary, int maxSalary) {
           Predicate<Employee> predicate = e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary;
        return findEmployeesByPredicate(predicate);
        }

        private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate){
        List<Employee> res = new ArrayList<>();
            for (Employee employee: employees) {
                if (predicate.test(employee)){
                    res.add(employee);
                }
            }
            return res.toArray(new Employee[res.size()]);//!!!NB
        }

}



