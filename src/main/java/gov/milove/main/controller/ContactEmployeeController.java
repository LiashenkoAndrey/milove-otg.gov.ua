package gov.milove.controllers;

import gov.milove.controllers.abstr.IContactEmployeeController;
import gov.milove.domain.ContactEmployee;
import gov.milove.repositories.jpa.ContactEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactEmployeeController implements IContactEmployeeController {

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
