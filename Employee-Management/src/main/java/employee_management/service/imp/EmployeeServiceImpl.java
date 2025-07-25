package employee_management.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import employee_management.dto.EmployeeDTO;
import employee_management.entity.Employee;
import employee_management.payload.PaginatedResponse;
import employee_management.repository.EmployeeRepository;
import employee_management.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;

    private EmployeeDTO convertToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(emp, dto);
        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        BeanUtils.copyProperties(dto, emp);
        return emp;
    }

    public EmployeeDTO create(EmployeeDTO dto) {
        Employee saved = repository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    public List<EmployeeDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return convertToDTO(emp);
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setRole(dto.getRole());
        emp.setSalary(dto.getSalary());
        return convertToDTO(repository.save(emp));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<EmployeeDTO> findByRole(String role) {
        return repository.findByRole(role).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> findBySalaryRange(double min, double max) {
        return repository.findBySalaryBetween(min, max).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public double getAverageSalaryByRole(String role) {
        List<Employee> employees = repository.findByRole(role);
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average().orElse(0.0);
    }
    
    public PaginatedResponse<EmployeeDTO> getPaginatedSorted(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Employee> pageResult = repository.findAll(pageable);
        
        List<EmployeeDTO> dtoList = pageResult.getContent()
            .stream()
            .map(this::convertToDTO)
            .toList();

        return new PaginatedResponse<>(
            dtoList,
            pageResult.getNumber(),
            pageResult.getTotalPages(),
            pageResult.getTotalElements()
        );
    }

    public List<EmployeeDTO> bulkInsert(List<EmployeeDTO> dtos) {
        List<Employee> employees = dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        return repository.saveAll(employees).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO toggleActiveStatus(Long id, boolean active) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setActive(active);
        return convertToDTO(repository.save(emp));
    }

    public List<EmployeeDTO> getAllActive() {
        return repository.findByActive(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}