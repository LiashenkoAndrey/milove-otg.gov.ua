package gov.milove.main.repository.jpa;

import gov.milove.main.domain.TextBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBannerRepository extends JpaRepository<TextBanner, Long> {
}
