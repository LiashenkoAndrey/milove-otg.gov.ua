package gov.milove.main.controller;

import gov.milove.main.domain.Document;
import gov.milove.main.dto.DocumentGroupWithGroupsDto;
import gov.milove.main.dto.DocumentGroupWithGroupsDtoAndDocumentsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Document group controller")
@RequestMapping("/api")
@Validated
public interface DocumentGroupController {

    @Operation(summary = "Get the document list")
    @GetMapping("/documentGroup/all")
    List<DocumentGroupWithGroupsDto> findAll();

    @Operation(summary = "Create a subgroup of documents")
    @PostMapping("/protected/documentGroup/new")
    DocumentGroupWithGroupsDtoAndDocumentsDto createNewSubGroup(@RequestParam(required = false) Long groupId, @NotBlank @RequestParam String name);

    @Operation(summary = "Edit a subgroup of documents")
    @PutMapping("/protected/documentGroup/{id}/update")
    Long editSubGroup(@PathVariable Long id, @NotBlank @RequestParam String name);

    @Operation(summary = "Delete a subgroup of documents")
    @DeleteMapping("/protected/documentGroup/{id}/delete")
    Long deleteSubGroup(@PathVariable Long id);

    @Operation(summary = "Create a new document")
    @PostMapping("/protected/documentGroup/{id}/document/new")
    Document newDoc(@PathVariable Long id, @RequestParam MultipartFile file, @RequestParam String title);

    @Operation(summary = "Find document group by id")
    @GetMapping("/documentGroup/id/{id}")
    DocumentGroupWithGroupsDtoAndDocumentsDto findById(@PathVariable Long id);
}
