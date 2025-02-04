package gov.milove.main.service;

import gov.milove.main.domain.LinkBanner;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import java.util.List;

public interface LinkBannerService {

  List<LinkBanner> findAllBanners();

  LinkBanner save(LinkBanner linkBanner);

  LinkBanner update(LinkBannerUpdateRequest request);

  void deleteById(Long id);
}
