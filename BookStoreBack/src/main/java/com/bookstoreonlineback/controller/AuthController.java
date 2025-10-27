package com.bookstoreonlineback.controller;

import com.bookstoreonlineback.DTO.AuthResponseDTO;
import com.bookstoreonlineback.DTO.LoginRequestDTO;
import com.bookstoreonlineback.DTO.RegisterRequestDTO;
import com.bookstoreonlineback.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public AuthResponseDTO signUp(@RequestBody RegisterRequestDTO request) throws BadRequestException {
        log.info("AuthController - signUp - begin");
        AuthResponseDTO authResponseDTO = authService.register(request);
        log.info("AuthController - signUp - end ok");
        return authResponseDTO;
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public AuthResponseDTO logIn (@RequestBody LoginRequestDTO request) {
        log.info("AuthController - login -begin");
        AuthResponseDTO authResponseDTO = authService.login(request);
        log.info("AuthController - login - end ok");
        return authResponseDTO;
    }
}
