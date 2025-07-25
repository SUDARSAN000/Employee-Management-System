package employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employee_management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByRole(String role);
    List<Employee> findBySalaryBetween(double min, double max);
    List<Employee> findByActive(boolean active);
}
