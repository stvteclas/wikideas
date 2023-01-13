package com.idforideas.wikideas.dto;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {
    private String message;
}
