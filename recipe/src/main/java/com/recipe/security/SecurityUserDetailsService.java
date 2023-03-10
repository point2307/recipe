package com.recipe.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recipe.dto.Member;
import com.recipe.persistence.MemberRepo;


@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepo mrepo;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<Member> optional = mrepo.findById(userId);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(userId + "사용자 없음");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
		
	}
}
