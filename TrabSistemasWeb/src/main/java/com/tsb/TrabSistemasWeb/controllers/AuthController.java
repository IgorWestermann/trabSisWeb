package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.dto.LoginRequestDto;
import com.tsb.TrabSistemasWeb.dto.RegisterRequestDto;
import com.tsb.TrabSistemasWeb.dto.ResponseDto;
import com.tsb.TrabSistemasWeb.dto.UserResponseDto;
import com.tsb.TrabSistemasWeb.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginRequestDto body) {
        ResponseDto responseDTO = authService.login(body);

        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody RegisterRequestDto body) {
        UserResponseDto response = authService.createNewUser(body);

        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
