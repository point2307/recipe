package com.recipe.service;

import com.recipe.dto.Member;
import com.recipe.dto.Role;
import com.recipe.dto.Token;
import com.recipe.persistence.MemberRepo;
import com.recipe.persistence.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TokenService {
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TokenRepo tokenRepo;

    public String createTokenMail(String userId, String email){
        Assert.hasText(userId,"userId는 필수입니다");

        Token token = Token.makeToken(userId);
        tokenRepo.save(token);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("회원가입 인증 메일");
        mailMessage.setText("http://localhost:8080/member/checkMailToken?token="+token.getId());
        mailService.mailSend(mailMessage);

        return token.getId();

    }


    public Token findById(String tokenId){
        Optional<Token> token= tokenRepo.findByIdAndExpired(tokenId, false);
        return token.orElseThrow(()-> new IllegalStateException());
    }

    public void confirmMail(String token){
        Token findToken = findById(token);
        findToken.setExpired(true);
        Member member = memberRepo.findById(findToken.getUserId()).get();
        member.setRole(Role.ROLE_CUSTOMER);
        memberRepo.save(member);
    }

}
