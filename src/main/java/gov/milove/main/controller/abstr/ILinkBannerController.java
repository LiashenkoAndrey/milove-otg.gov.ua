package gov.milove.controllers.abstr;

import gov.milove.domain.LinkBanner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Link banner controller")
@RequestMapping("/api/link-banner")
public interface ILinkBannerController {

    @Operation(summary = "Get all link banners")
    List<LinkBanner> getAll();

    @Operation(summary = "Create link banner")
    ResponseEntity<Long> createBanner(@RequestBody LinkBanner banner);

    @Operation(summary = "Update link banner")
    ResponseEntity<?> update(@RequestBody LinkBanner banner);

    @Operation(summary = "Delete link banner")
    @DeleteMapping("/delete")
    ResponseEntity<?> delete(@RequestParam("id") Long id);
}
