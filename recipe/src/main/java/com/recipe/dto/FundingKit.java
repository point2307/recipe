package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@Entity
public class FundingKit {
    @Id
    @SequenceGenerator(name = "fundkit_seq", sequenceName = "fundkit_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fundkit_seq")
    private Long fundKitId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "funding")
    private Funding funding;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mealkit")
    private Mealkit mealkit;

    @Transient
    private int selling;

    @PrePersist
    public void prePersist(){
        this.selling = 0;
    }
}
