package com.idforideas.wikideas.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleResponseDTO {
    private String title;
}
