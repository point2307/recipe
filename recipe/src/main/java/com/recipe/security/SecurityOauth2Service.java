package com.recipe.security;

import com.recipe.persistence.MemberRepo;
import jakarta.servlet.http.HttpSession;
/*
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.recipe.dto.Member;

import java.util.Collections;
import java.util.Optional;


@Service
public class SecurityOauth2Service implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {

    private final MemberRepo memberRepo;

    private final HttpSession httpSession;

    public SecurityOauth2Service(MemberRepo memberRepo, HttpSession httpSession) {
        this.memberRepo = memberRepo;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attr = OAuthAttributes.of(registrationId,userNameAttributeName,oauth2User.getAttributes());
        Member member = SaveOrUpdate(attr);
        httpSession.setAttribute("user", member);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())),
                attr.getAttributes(), attr.getNameAttributeKey()
        );
    }

    private Member SaveOrUpdate(OAuthAttributes attr){
        Optional search = memberRepo.findByEmail(attr.getEmail());
        Member member = new Member();
        if(search == null || search.isEmpty()){
            member = attr.toEntity();
        } else{
            member = (Member) search.get();
        }
        return memberRepo.save(member);

    }
}
*/