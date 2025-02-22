package gov.milove.repositories.jpa;

import gov.milove.domain.TextBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBannerRepository extends JpaRepository<TextBanner, Long> {
}
