package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class MyMaterial {
    @Id
    @GeneratedValue
    private Long myMaterId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    private Material material;

    private int amount;

}
