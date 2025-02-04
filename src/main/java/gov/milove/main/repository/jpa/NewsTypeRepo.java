package gov.milove.main.repository.jpa;

import gov.milove.main.domain.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NewsTypeRepo extends JpaRepository<NewsType, Long> {


}
