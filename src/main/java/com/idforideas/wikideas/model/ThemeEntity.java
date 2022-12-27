package com.idforideas.wikideas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

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

//    @OneToMany(mappedBy="theme", cascade = CascadeType.ALL)
//    private Set<ArticleEntity> Article;

}
