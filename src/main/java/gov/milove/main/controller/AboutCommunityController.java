package gov.milove.controllers;

import gov.milove.controllers.abstr.IAboutCommunityController;
import gov.milove.domain.About;
import gov.milove.repositories.mongo.AboutRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AboutCommunityController implements IAboutCommunityController {

    private final AboutRepo aboutRepo;

    @Override
    public About getPage() {
        return aboutRepo.findById("663f6e32388652544c7ff08f").orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getUpdatePage(@RequestParam String mainText) {
        About about =  aboutRepo.findById("663f6e32388652544c7ff08f").orElseThrow(EntityNotFoundException::new);
        about.setMainText(mainText);
        aboutRepo.save(about);
        return about.getId();
    }

}
