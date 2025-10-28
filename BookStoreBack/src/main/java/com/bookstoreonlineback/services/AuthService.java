package com.bookstoreonlineback.services;

import com.bookstoreonlineback.DTO.responses.AuthResponseDTO;
import com.bookstoreonlineback.DTO.requests.LoginRequestDTO;
import com.bookstoreonlineback.DTO.requests.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login (LoginRequestDTO request);
}
