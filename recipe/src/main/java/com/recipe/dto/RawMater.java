package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString(exclude = {"mater","recipe"})
public class RawMater {
    @Id
    @SequenceGenerator(name = "raw_seq", sequenceName = "raw_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raw_seq")
    private Long rawId;
    @OneToOne
    @JoinColumn(name="mater")
    private Material mater;
    @ManyToOne
    @JoinColumn(name = "recipe")
    private Recipe recipe;
    private String amount;


}
