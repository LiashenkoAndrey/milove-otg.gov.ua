package gov.milove.main.repository.jpa;

import gov.milove.main.domain.News;
import gov.milove.main.dto.INewsDto;
import gov.milove.main.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<INewsDto> findDistinctBy(Pageable pageable);

    @Query(
            """
                      select distinct 
                            n.id as id, 
                            n.images as images, 
                            n.description as description,
                            n.dateOfPublication as created, 
                            n.views as views, 
                            n.newsType as newsType
                    FROM News n where n.newsType.id = :type_id and n.id != :news_id 
                    """
    )
    Page<INewsDto> getLastNewsDTOByNewsTypeIdWithLimit(@Param("news_id") Long news_id, @Param("type_id") Long newTypeId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update News n set n.views = n.views + 1 where n.id = :newsId")
    void incrementViews(@Param("newsId") Long id);
}
