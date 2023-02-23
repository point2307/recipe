package com.recipe.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Token {
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        private String id;

        private boolean expired;

        private String userId;

        @CreatedDate
        private Date createDate;

        private Date expirationDate;


        public static Token makeToken(String userId){
            Token confirmationToken = new Token();
            Date now = new Date();
            now.setMinutes(now.getMinutes()+15);
            confirmationToken.expirationDate = now;
            confirmationToken.userId = userId;
            confirmationToken.expired = false;
            return confirmationToken;
        }
        // 쓰면 삭제
        public void useToken(){
            expired = true;
        }
}

