package com.idforideas.wikideas.exception;

import lombok.Getter;

@Getter
public enum MessageErrorEnum {

    INVALID_TITLE("Invalid title or not exist this article"),
    ARTICLE_EXISTS("Article already exists"),

    MANDATORY_PARAMETERES_MISSING("Mandatory Parameters Missing"),

    WRONG_PARAMETERS("Wrong parameters");

    private String message;

    MessageErrorEnum(String message) {
        this.message = message;
    }
}
