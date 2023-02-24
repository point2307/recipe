package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Notice {
    @Id
    @GeneratedValue
    private Long noticeId;

    private int kind;

    private String title;

    private String content;

    private String image;

    private String keyword;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date regDate;

}
