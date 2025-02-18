package gov.milove.main.controller;

import gov.milove.main.domain.Document;
import gov.milove.main.dto.DocumentWithGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Document controller")
@RequestMapping("/api")
public interface DocumentController {
    @Operation(summary = "Update a document name")
    @PutMapping("/protected/document/{id}/update")
    Long updateDocumentName(@PathVariable Long id,
                            @NotBlank @RequestParam String name);

    @Operation(summary = "Delete document")
    @DeleteMapping("/protected/document/{id}/delete")
    Document deleteDocument(@PathVariable Long id);

    @Operation(summary = "Search a document")
    @GetMapping("/documents/search")
    List<DocumentWithGroupDto> searchDocs(@RequestParam(name = "docName") String encodedString);
}
