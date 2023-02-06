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
    @GeneratedValue
    private Long rawId;
    @OneToOne
    private Material mater;
    private int amount;

}
