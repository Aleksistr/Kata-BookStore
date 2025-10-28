package com.bookstoreonlineback.services.impl;

import com.bookstoreonlineback.DTO.responses.AuthResponseDTO;
import com.bookstoreonlineback.DTO.requests.LoginRequestDTO;
import com.bookstoreonlineback.DTO.requests.RegisterRequestDTO;
import com.bookstoreonlineback.entities.User;
import com.bookstoreonlineback.reppositories.UserRepository;
import com.bookstoreonlineback.security.JwtService;
import com.bookstoreonlineback.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponseDTO(token);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponseDTO(token);
    }

}
