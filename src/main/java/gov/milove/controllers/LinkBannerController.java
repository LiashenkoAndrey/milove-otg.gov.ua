package gov.milove.controllers;

import gov.milove.controllers.abstr.ILinkBannerController;
import gov.milove.domain.LinkBanner;
import gov.milove.repositories.jpa.LinkBannerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static gov.milove.util.EntityMapper.map;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class LinkBannerController implements ILinkBannerController {

    private final LinkBannerRepository repo;

    @Override
    public List<LinkBanner> getAll() {
        return repo.findAll(Sort.by("createdOn").descending());
    }

    @Override
    public ResponseEntity<Long> createBanner(LinkBanner banner) {
        if (repo.exists(Example.of(banner))) {
            return ResponseEntity.status(CONFLICT).build();
        } else {
            LinkBanner saved = repo.save(banner);
            return ResponseEntity.status(CREATED).body(saved.getId());
        }
    }

    @Override
    public ResponseEntity<?> update(LinkBanner banner) {
        if (banner.getId() == null) return ResponseEntity.badRequest().build();
        LinkBanner saved = repo.findById(banner.getId()).orElseThrow(EntityNotFoundException::new);

        map(banner, saved).mapEmptyString(false).map();

        repo.save(saved);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
