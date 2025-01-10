package gov.milove.repositories.jpa.media;

import gov.milove.domain.media.TextBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBannerRepository extends JpaRepository<TextBanner, Long> {
}
