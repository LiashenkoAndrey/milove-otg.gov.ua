package gov.milove.main.controller;

import gov.milove.main.domain.LinkBanner;
import gov.milove.main.repository.jpa.LinkBannerRepository;
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
@RequestMapping("/api/link-banner")
@RequiredArgsConstructor
public class LinkBannerController {

    private final LinkBannerRepository repo;

    @GetMapping("/all")
    public List<LinkBanner> getAll() {
        return repo.findAll(Sort.by("createdOn").descending());
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createBanner(@RequestBody LinkBanner banner) {
        if (repo.exists(Example.of(banner))) {
            return ResponseEntity
                    .status(CONFLICT)
                    .build();
        } else {
            LinkBanner saved = repo.save(banner);
            return ResponseEntity
                    .status(CREATED)
                    .body(saved.getId());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody LinkBanner banner) {
        if (banner.getId() == null) return ResponseEntity.badRequest().build();
        LinkBanner saved = repo.findById(banner.getId()).orElseThrow(EntityNotFoundException::new);

        EntityMapper.map(banner, saved)
                .mapEmptyString(false)
                .map();

        repo.save(saved);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        repo.deleteById(id);
    }
}
