package com.shop.vympel.services;

import com.shop.vympel.db.entity.auth.Role;
import com.shop.vympel.db.entity.auth.User;
import com.shop.vympel.db.entity.auth.UserRole;
import com.shop.vympel.db.repositories.RoleRepository;
import com.shop.vympel.db.repositories.UserRepository;
import com.shop.vympel.db.repositories.UserRoleRepository;
import com.shop.vympel.dtos.auth.AuthResponse;
import com.shop.vympel.dtos.auth.RegisterByEmailRequest;
import com.shop.vympel.mappers.UserMapper;
import com.shop.vympel.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public AuthResponse register(RegisterByEmailRequest req) throws IllegalArgumentException {
        String email = req.getEmail();
        User checkUnique = userRepository.findByEmail(email).orElse(null);

        if (checkUnique!=null) {
            throw new IllegalArgumentException("Email already exists");
        }

        User newUser = userMapper.toEntity(req, passwordEncoder);
        userRepository.save(newUser);

        Role role = roleRepository.getReferenceById(2L);

        UserRole userRole = UserRole.of(newUser, role);
        userRoleRepository.save(userRole);

        return createTokens(String.valueOf(newUser.getId()), List.of(role.getCode()));
    }


    private AuthResponse createTokens(String subject, List<String> role) {

        String accessToken = jwtService.generateAccessToken(subject, role);
        String refreshToken = jwtService.generateRefreshToken(subject);

        return new AuthResponse(accessToken, refreshToken);
    }
}
