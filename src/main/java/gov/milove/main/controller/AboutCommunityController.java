package gov.milove.main.controller;

import gov.milove.main.domain.About;
import gov.milove.main.repository.mongo.AboutRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AboutCommunityController {

    private final AboutRepo aboutRepo;

    @GetMapping("/aboutCommunity")
    public About getPage() {
        return aboutRepo.findById("663f6e32388652544c7ff08f").orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/protected/aboutCommunity/update")
    public String getUpdatePage(@RequestParam String mainText) {
        About about =  aboutRepo.findById("663f6e32388652544c7ff08f").orElseThrow(EntityNotFoundException::new);
        about.setMainText(mainText);
        aboutRepo.save(about);
        return about.getId();
    }
}
