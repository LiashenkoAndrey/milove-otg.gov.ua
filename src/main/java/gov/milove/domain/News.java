package gov.milove.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    public News(NewsType newsType) {
        this.newsType = newsType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 120)
    @NotNull
    private String description;

    @NotNull
    private String main_text;

    @NotNull
    private LocalDateTime dateOfPublication;

    private String image_id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "newsId")
    private List<NewsImage> images = new ArrayList<>();

    private LocalDateTime last_updated;

    private Long views = 0L;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private NewsType newsType;
}
