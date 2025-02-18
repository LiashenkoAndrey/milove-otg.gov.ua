package gov.milove.main.controller.impl;

import gov.milove.main.controller.ContactEmployeeController;
import gov.milove.main.domain.ContactEmployee;
import gov.milove.main.repository.jpa.ContactEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactEmployeeControllerImpl implements ContactEmployeeController {

    private final ContactEmployeeRepository repository;


    @Override
    public List<ContactEmployee> getAll() {
        return repository.findAll();
    }


    @Override
    public ContactEmployee create(ContactEmployee employee) {
        repository.save(employee);
        return employee;
    }

    @Override
    public Long update(Long id, ContactEmployee updatedEmployee) {
        updatedEmployee.setId(id);
        repository.save(updatedEmployee);
        return id;
    }

    @Override
    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
    }
}
