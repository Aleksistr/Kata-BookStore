package com.bookstoreonlineback.services;

import com.bookstoreonlineback.DTO.AuthResponseDTO;
import com.bookstoreonlineback.DTO.LoginRequestDTO;
import com.bookstoreonlineback.DTO.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login (LoginRequestDTO request);
}
