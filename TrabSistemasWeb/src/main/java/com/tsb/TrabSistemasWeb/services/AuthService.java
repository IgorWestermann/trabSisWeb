package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.dto.LoginRequestDto;
import com.tsb.TrabSistemasWeb.dto.RegisterRequestDto;
import com.tsb.TrabSistemasWeb.dto.ResponseDto;
import com.tsb.TrabSistemasWeb.dto.UserResponseDto;
import com.tsb.TrabSistemasWeb.infra.security.TokenService;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public ResponseDto login(LoginRequestDto body) {
        User user = this.userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.GenereteToken(user);
            return new ResponseDto(user.getName(), token);
        }

        return null;
    }

    public UserResponseDto createNewUser(RegisterRequestDto body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setName(body.name());
            newUser.setPhone(body.phone());
            newUser.setRole(body.role());

            this.userRepository.save(newUser);

            return new UserResponseDto(
                    newUser.getId(),
                    newUser.getName(),
                    newUser.getEmail(),
                    newUser.getPhone(),
                    newUser.getRole());
        }
        return null;
    }

}
