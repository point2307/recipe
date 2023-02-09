package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"fund","kit","member"})
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Member member;

    @OneToOne
    private Funding fund;
    @OneToOne
    private Mealkit kit;

    private int quantity;
}
