package gov.milove.main.controller;

import gov.milove.main.domain.About;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name="About community controller")
@RestController
@RequestMapping("/api")
public interface AboutCommunityController {

    @Operation(summary = "Get the about community page")
    @GetMapping("/aboutCommunity")
    About getPage();

    @Operation(summary = "Update the about community page")
    @PutMapping("/protected/aboutCommunity/update")
    String getUpdatePage(@RequestParam String mainText);
}
