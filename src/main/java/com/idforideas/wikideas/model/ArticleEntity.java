package com.idforideas.wikideas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
@SQLDelete(sql = "UPDATE article SET deleted = true WHERE article_id = ?")
@Where(clause = "deleted = false")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long idArticle;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "text", length = 429496729 )
    @NotNull
    private String text;

    @Column(name = "creationDate")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "updateDate")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    private boolean deleted = Boolean.FALSE;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "idTheme", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private ThemeEntity theme;
}
