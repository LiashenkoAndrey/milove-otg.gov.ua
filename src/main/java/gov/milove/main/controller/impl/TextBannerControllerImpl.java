package gov.milove.main.controller.impl;

import gov.milove.main.controller.TextBannerController;
import gov.milove.main.domain.TextBanner;
import gov.milove.main.repository.jpa.TextBannerRepository;
import gov.milove.main.util.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class TextBannerControllerImpl implements TextBannerController {

    private final TextBannerRepository repo;

    @Override
    public List<TextBanner> getAll() {
        return repo.findAll(Sort.by("createdOn").descending());
    }

    @Override
    public ResponseEntity<Long> createBanner(TextBanner banner) {
        if (repo.exists(Example.of(banner))) {
            return ResponseEntity.status(CONFLICT).build();
        } else {
            TextBanner saved = repo.save(banner);
            return ResponseEntity.status(CREATED).body(saved.getId());
        }
    }


    @Override
    public ResponseEntity<?> update(TextBanner banner) {
        if (banner.getId() == null) return ResponseEntity.badRequest().build();
        TextBanner saved = repo.findById(banner.getId()).orElseThrow(EntityNotFoundException::new);

        EntityMapper.map(banner, saved).mapEmptyString(false).mapNull(false).map();

        repo.save(saved);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
