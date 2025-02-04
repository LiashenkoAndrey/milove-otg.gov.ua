package gov.milove.main.util.mapper;

import gov.milove.main.domain.LinkBanner;
import gov.milove.main.dto.LinkBannerDto;
import gov.milove.main.dto.request.LinkBannerCreateRequest;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = MapperConfig.class
)
public interface LinkBannerMapper {

  LinkBannerDto toLinkBannerDto(LinkBanner linkBanner);

  LinkBanner toLinkBanner(LinkBannerCreateRequest request);

  void updateLinkBannerFromDto(LinkBannerUpdateRequest request, @MappingTarget LinkBanner linkBanner);
}
