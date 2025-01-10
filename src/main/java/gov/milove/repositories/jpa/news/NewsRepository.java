package gov.milove.repositories.jpa.news;

import gov.milove.domain.news.News;
import gov.milove.domain.dto.news.INewsDto;
import gov.milove.domain.dto.news.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News, Long> {


@Query("""
    SELECT n.id AS id,
           n.images AS images,
           n.description AS description,
           n.dateOfPublication AS dateOfPublication,
           n.views AS views,
           n.newsType AS newsType,
           n.image_id as image_id,
           COUNT(c.id) AS commentsAmount
    FROM News n
    LEFT JOIN NewsComment c ON c.newsId = n.id
    GROUP BY n.id, n.images, n.description, n.dateOfPublication, n.views, n.newsType
    ORDER BY n.dateOfPublication DESC
""")
    Page<INewsDto> findDistinctBy(Pageable pageable);

    @Query(
            """
            select distinct
                n.id as id,
                n.images as images,
                n.description as description,
                n.dateOfPublication as created,
                n.views as views,
                n.newsType as newsType,
                n.image_id as image_id,
                COUNT(c.id) AS commentsAmount
            FROM News n 
            LEFT JOIN NewsComment c ON c.newsId = n.id
            WHERE n.newsType.id = :type_id and n.id != :news_id
            GROUP BY n.id, n.images, n.description, n.dateOfPublication, n.views, n.newsType
            ORDER BY n.dateOfPublication DESC
            """
    )
    Page<INewsDto> getLastNewsDTOByNewsTypeIdWithLimit(@Param("news_id") Long news_id, @Param("type_id") Long newTypeId, Pageable pageable);


    @Query(
            "from News n order by n.dateOfPublication desc"
    )
    Page<NewsDTO> getLatest(Pageable pageable);


    @Modifying
    @Transactional
    @Query("update News n set n.views = n.views + 1 where n.id = :newsId")
    void incrementViews(@Param("newsId") Long id);
}
