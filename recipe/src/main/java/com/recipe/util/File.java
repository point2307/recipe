package com.recipe.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class File {
    private String uuid;
    private String originalName;

    private String contentType;

    public File(){}

    public File(String uuid, String originalName, String contentType) {
        this.uuid = uuid;
        this.originalName = originalName;
        this.contentType = contentType;
    }
}
