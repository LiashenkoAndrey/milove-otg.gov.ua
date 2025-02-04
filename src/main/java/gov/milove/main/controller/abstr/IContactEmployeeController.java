package gov.milove.controllers.abstr;

import gov.milove.domain.ContactEmployee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Contact employee controller")
@RestController
@RequestMapping("/api")
public interface IContactEmployeeController {

    @Operation(summary = "Get the available employees")
    @GetMapping("/contacts")
    List<ContactEmployee> getAll();

    @Operation(summary = "Add an employee")
    @PostMapping("/protected/contact/new")
    ContactEmployee create(@RequestBody ContactEmployee employee);

    @Operation(summary = "Update an employee")
    @PostMapping("/contact/update")
    Long update(
            @RequestParam("id") Long id,
            @RequestBody ContactEmployee updatedEmployee);

    @Operation(summary = "Delete an employee")
    @DeleteMapping("/protected/contact/{id}/delete")
    Long delete(@PathVariable Long id);
}
