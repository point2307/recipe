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
@ToString(exclude = {"buy", "mealkit", "funding"})
public class BuyDetail {

    @Id
    @SequenceGenerator(name = "buyDetailSeq", sequenceName = "buyDetailSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buyDetailSeq")
    private Long buyDeId;

    @ManyToOne
    @JoinColumn(name = "buy")
    private Buy buy;

    @OneToOne
    @JoinColumn(name = "mealKit")
    private Mealkit mealkit;

    @OneToOne
    @JoinColumn(name = "funding")
    private Funding funding;

    private int quantity;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date buyDate;

    private String process;

}
