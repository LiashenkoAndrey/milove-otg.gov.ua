package gov.milove.main.repository.jpa;

import gov.milove.main.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
}
