package gov.milove.controllers.abstr;

import gov.milove.domain.TextBanner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Text banner controller")
@RequestMapping("/api/text-banner")
public interface ITextBannerController {

    @Operation(summary = "Get all text banners")
    @GetMapping("/all")
    List<TextBanner> getAll();

    @Operation(summary = "Create text banner")
    @PostMapping("/new")
    ResponseEntity<Long> createBanner(@RequestBody TextBanner banner);

    @Operation(summary = "Update text banner")
    @PutMapping("/update")
    ResponseEntity update(@RequestBody TextBanner banner);

    @Operation(summary = "Delete text banner")
    @DeleteMapping("/delete")
    ResponseEntity delete(@RequestParam("id") Long id);
}
