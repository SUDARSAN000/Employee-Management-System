package employee_management.service;

import java.util.List;

import employee_management.dto.EmployeeDTO;
import employee_management.payload.PaginatedResponse;

public interface EmployeeService {
	 EmployeeDTO create(EmployeeDTO dto);
	    List<EmployeeDTO> getAll();
	    EmployeeDTO getById(Long id);
	    EmployeeDTO update(Long id, EmployeeDTO dto);
	    void delete(Long id);
	    List<EmployeeDTO> findByRole(String role);
	    List<EmployeeDTO> findBySalaryRange(double min, double max);
	    double getAverageSalaryByRole(String role);
	    PaginatedResponse<EmployeeDTO> getPaginatedSorted(int page, int size, String sortBy);
	    List<EmployeeDTO> bulkInsert(List<EmployeeDTO> employees);
	    EmployeeDTO toggleActiveStatus(Long id, boolean active);
	    List<EmployeeDTO> getAllActive();
}
