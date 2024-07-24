package com.com4in.itsm.service;

import com.com4in.itsm.dto.UserDto;
import com.com4in.itsm.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        UserDto userDto = userMapper.getPersonID(username);

        if (userDto == null) {
            throw new UsernameNotFoundException(username + " -> 찾을 수 없습니다.");
        }

        return createUser(username, userDto);


    }

    private org.springframework.security.core.userdetails.User createUser(String username, UserDto userDto ) {

//        System.out.println("personDto======================="+personDto);

//        if (!user.isActivated()) {
//            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
//        }

//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));

//        return null;
        return new org.springframework.security.core.userdetails.User(userDto.getEmail(),
                userDto.getPassword(),
                grantedAuthorities);
    }
}
