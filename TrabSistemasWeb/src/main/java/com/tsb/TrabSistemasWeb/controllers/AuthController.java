package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.user.User;
import com.tsb.TrabSistemasWeb.dto.LoginRequestDto;
import com.tsb.TrabSistemasWeb.dto.RegisterRequestDTO;
import com.tsb.TrabSistemasWeb.dto.ResponseDTO;
import com.tsb.TrabSistemasWeb.infra.security.TokenService;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginRequestDto body) {
        User user = this.userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.GenereteToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setName(body.name());

            this.userRepository.save(newUser);

            String token = this.tokenService.GenereteToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }


        return ResponseEntity.badRequest().build();
    }
}
