package gov.milove.main.controller;

import gov.milove.main.domain.TextBanner;
import gov.milove.main.repository.jpa.TextBannerRepository;
import gov.milove.main.util.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/text-banner")
@RequiredArgsConstructor
public class TextBannerController {

    private final TextBannerRepository repo;

    @GetMapping("/all")
    public List<TextBanner> getAll() {
        return repo.findAll(Sort.by("createdOn").descending());
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createBanner(@RequestBody TextBanner banner) {
        if (repo.exists(Example.of(banner))) {
            return ResponseEntity
                    .status(CONFLICT)
                    .build();
        } else {
            TextBanner saved = repo.save(banner);
            return ResponseEntity
                    .status(CREATED)
                    .body(saved.getId());
        }
    }


    @PutMapping("/update")
    public void update(@RequestBody TextBanner banner) {
        TextBanner saved = repo.findById(banner.getId()).orElseThrow(EntityNotFoundException::new);

        EntityMapper.map(banner, saved)
                .mapEmptyString(false)
                .mapNull(false)
                .map();

        repo.save(saved);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        repo.deleteById(id);
    }
}
