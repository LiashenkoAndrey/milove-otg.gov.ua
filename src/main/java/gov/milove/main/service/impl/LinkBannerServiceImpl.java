package gov.milove.main.service.impl;

import gov.milove.main.domain.LinkBanner;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import gov.milove.main.exception.LinkBannerNotFoundException;
import gov.milove.main.repository.jpa.LinkBannerRepository;
import gov.milove.main.service.LinkBannerService;
import gov.milove.main.util.mapper.LinkBannerMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class LinkBannerServiceImpl implements LinkBannerService {

  private final LinkBannerRepository linkBannerRepository;

  private final LinkBannerMapper linkBannerMapper;

  @Override
  public List<LinkBanner> findAllBanners() {
    return linkBannerRepository.findAll(
        Sort.by("createdOn").descending()
    );
  }

  @Override
  public LinkBanner save(LinkBanner linkBanner) {
    return linkBannerRepository.save(linkBanner);
  }

  @Override
  public LinkBanner update(LinkBannerUpdateRequest request) {
    LinkBanner linkBanner = findById(request.id());
    linkBannerMapper.updateLinkBannerFromDto(request, linkBanner);
    return linkBannerRepository.save(linkBanner);
  }

  @Override
  public void deleteById(Long id) {
    findById(id);
    linkBannerRepository.deleteById(id);
  }

  private LinkBanner findById(Long id) {
    return linkBannerRepository.findById(id).orElseThrow(() ->
        new LinkBannerNotFoundException("Link banner with id: %s not found".formatted(id)));
  }
}
