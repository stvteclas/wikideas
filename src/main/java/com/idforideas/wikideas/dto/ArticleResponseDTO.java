package com.idforideas.wikideas.dto;

import lombok.*;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleResponseDTO implements Serializable {
    private String title;
}
