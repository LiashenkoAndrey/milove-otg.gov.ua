package gov.milove.main.repository.jpa;

import gov.milove.main.domain.LinkBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkBannerRepository extends JpaRepository<LinkBanner, Long> {
}
