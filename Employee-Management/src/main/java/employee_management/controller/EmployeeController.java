package employee_management.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee_management.dto.EmployeeDTO;
import employee_management.payload.PaginatedResponse;
import employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<EmployeeDTO>> getByRole(@PathVariable String role) {
        return ResponseEntity.ok(service.findByRole(role));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeDTO>> getBySalaryRange(
            @RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(service.findBySalaryRange(min, max));
    }

    @GetMapping("/average-salary/{role}")
    public ResponseEntity<Double> getAverageSalary(@PathVariable String role) {
        return ResponseEntity.ok(service.getAverageSalaryByRole(role));
    }

 
    @PostMapping("/bulk")
    public ResponseEntity<List<EmployeeDTO>> bulkInsert(@RequestBody List<EmployeeDTO> dtos) {
        return new ResponseEntity<>(service.bulkInsert(dtos), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeDTO> toggleStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(service.toggleActiveStatus(id, active));
    }

    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDTO>> getActiveEmployees() {
        return ResponseEntity.ok(service.getAllActive());
    }
}