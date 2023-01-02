CREATE TABLE themes
(
    idTheme BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)          NULL,
    description VARCHAR(255)          NULL,
    CONSTRAINT pk_theme PRIMARY KEY (idTheme)
);
