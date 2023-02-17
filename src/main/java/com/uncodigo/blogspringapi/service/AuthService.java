package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.LoginDto;
import com.uncodigo.blogspringapi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
