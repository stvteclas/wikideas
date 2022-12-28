package com.idforideas.wikideas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ThemeEnum {
    HISTORY("HISTORY", 1L),
    COSMOLOGY("COSMOLOGY", 2L);


    private String name;
    private Long id;

}
