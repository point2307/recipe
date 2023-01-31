package com.recipe.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class File {
    @Id
    @GeneratedValue
    private Long file_id;
    private String originalName;
    private String uploadName;
    private String uploadFolder;
    private Date regDate;

}
