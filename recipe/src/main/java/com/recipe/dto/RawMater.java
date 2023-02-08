package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "mater")
@Entity
public class RawMater {
    @Id
    @SequenceGenerator(name = "raw_seq", sequenceName = "raw_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raw_seq")
    private Long rawId;
    @OneToOne
    private Material mater;
    private int amount;

}
