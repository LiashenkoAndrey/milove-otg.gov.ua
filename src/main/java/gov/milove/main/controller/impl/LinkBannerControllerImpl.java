package gov.milove.main.controller.impl;

import static org.springframework.http.HttpStatus.CREATED;

import gov.milove.main.controller.LinkBannerController;
import gov.milove.main.domain.LinkBanner;
import gov.milove.main.dto.LinkBannerDto;
import gov.milove.main.dto.request.LinkBannerCreateRequest;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import gov.milove.main.service.LinkBannerService;
import gov.milove.main.util.mapper.LinkBannerMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link-banners")
@RequiredArgsConstructor
@Log4j2
public class LinkBannerControllerImpl implements LinkBannerController {

    private final LinkBannerService linkBannerService;

    private final LinkBannerMapper linkBannerMapper;

    @Override
    @GetMapping
    public List<LinkBannerDto> findAll() {
        List<LinkBanner> linkBannerList = linkBannerService.findAllBanners();
        return linkBannerList.stream()
            .map(linkBannerMapper::toLinkBannerDto)
            .toList();
    }

    @Override
    @PostMapping
    public ResponseEntity<LinkBannerDto> addBanner(@RequestBody LinkBannerCreateRequest request) {
        log.info("Create link banner");
        LinkBanner linkBanner = linkBannerMapper.toLinkBanner(request);
        LinkBanner saved = linkBannerService.save(linkBanner);

        return new ResponseEntity<>(linkBannerMapper.toLinkBannerDto(saved), CREATED);
    }

    @Override
    @PutMapping
    public ResponseEntity<LinkBannerDto> update(@RequestBody LinkBannerUpdateRequest request) {
        LinkBanner linkBanner = linkBannerService.update(request);

        return new ResponseEntity<>(linkBannerMapper.toLinkBannerDto(linkBanner),
            HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Delete link banner by id: {}", id);
        linkBannerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
