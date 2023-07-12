package com.goit.controller;

import com.goit.dto.UserDto;
import com.goit.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DemoController {

    private final UserAccountService userAccountService;

    @GetMapping("/users")
    @Secured({"ADMIN"})
    public List<UserDto> users() {
        log.info("/users invocation");
        return userAccountService.all();
    }

    @PostMapping("/users")
    @Secured({"ADMIN", "SUPER_ADMIN"})
    public UserDto create(@RequestBody UserDto dto) {
        userAccountService.createUser(dto);
        return dto;
    }
}
