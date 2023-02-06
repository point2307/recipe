package com.recipe.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"fund","kit"})
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;

    @OneToOne
    private Funding fund;
    @OneToOne
    private Mealkit kit;

    private int quantity;
}
