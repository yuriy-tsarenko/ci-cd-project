package com.goit.service;

import com.goit.dto.UserDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private volatile List<UserDto> users = new ArrayList<>();

    @PostConstruct
    void setup() {
        UserDto userDto = UserDto.of(
                1L,
                "test@user",
                "$2a$10$PjsqdCi/kDdNSACHYG8Y0.ULu3HHKy.FU.OxrH3imkIr2/bq7PZyC",
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ADMIN"))
        );
        users.add(userDto);
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(userDto -> userDto.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void createUser(UserDetails user) {
        users.add((UserDto) user);
    }

    public List<UserDto> all() {
        return users;
    }
}
