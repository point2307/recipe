package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"member, material"})
public class MyMaterial {
    @Id
    @SequenceGenerator(name = "mymater_seq", sequenceName = "mymater_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mymater_seq")
    private Long myMaterId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    @JoinColumn(name = "material")
    private Material material;


}
