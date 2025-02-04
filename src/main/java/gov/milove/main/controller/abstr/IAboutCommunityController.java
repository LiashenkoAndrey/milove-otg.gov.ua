package gov.milove.controllers.abstr;

import gov.milove.domain.About;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name="About community controller")
@RestController
@RequestMapping("/api")
public interface IAboutCommunityController {

    @Operation(summary = "Get the about community page")
    @GetMapping("/aboutCommunity")
    About getPage();

    @Operation(summary = "Update the about community page")
    @PutMapping("/protected/aboutCommunity/update")
    String getUpdatePage(@RequestParam String mainText);
}
