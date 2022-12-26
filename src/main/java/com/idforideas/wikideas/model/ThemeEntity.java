package com.idforideas.wikideas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Theme")
public class ThemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTheme;

    private String name;

    private String description;

    @Column(name = "creationDate")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "updateDate")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idArticle", nullable = false)
    @JsonIgnore
    private ArticleEntity article;

}
