package com.recipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString(exclude = {"member", "board" ,"recipe", "funding", "reply","mealkit"})
public class Likey {
    @Id
    @SequenceGenerator(name = "likey_seq", sequenceName = "likey_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "likey_seq")
    private Long likeyId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Board board;

    @ManyToOne
    @JoinColumn(name= "recipe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "funding_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Funding funding;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "mealkit")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Mealkit mealkit;
}
