package com.recipe.security;

import com.recipe.dto.Member;
import com.recipe.dto.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    //사이트별 네이밍
    public static OAuthAttributes of(String registerationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    //각 사이트 상세옵션
    private static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .userId("oauthGoogle"+email)
                .name(name)
                .email(email)
                .role(Role.ROLE_CUSTOMER)
                .nickName(name)
                .proImg("noPic.jpg")
                .build();
    }

}
