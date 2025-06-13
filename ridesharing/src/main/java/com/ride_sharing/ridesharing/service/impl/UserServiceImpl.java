package com.ride_sharing.ridesharing.service.impl;

import com.ride_sharing.ridesharing.dto.LoginRequest;
import com.ride_sharing.ridesharing.dto.LoginResponse;
import com.ride_sharing.ridesharing.dto.UserDTO;
import com.ride_sharing.ridesharing.mapper.UserMapper;
import com.ride_sharing.ridesharing.model.User;
import com.ride_sharing.ridesharing.repository.UserRepository;
import com.ride_sharing.ridesharing.security.JwtUtil;
import com.ride_sharing.ridesharing.service.UserCacheService;
import com.ride_sharing.ridesharing.service.UserService;
import com.ride_sharing.ridesharing.utils.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserCacheService userCacheService;

    @Transactional
    @Override
    public UserDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findByIsActive(true)
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDelete(true);
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> cached = userCacheService.getUserFromCache(loginRequest.getEmail());
        User user = cached.orElseGet(() ->
                userRepository.findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new RuntimeException("Invalid credentials"))
        );

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        userRepository.updateLastLoginByEmail(loginRequest.getEmail());

        // Cập nhật cache TTL sau đăng nhập
        userCacheService.cacheUserOnLogin(user);

        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return new LoginResponse(token);
    }
    public void create1MUserSer() {
        String pass = passwordEncoder.encode("123456");

        int total = 2000000;
        int batchSize = 10000;

        List<User> users = new ArrayList<>();

        for (int i = 1000000; i < total; i++) {
            User newUser = new User();
            newUser.setName("Lê Bá Vũ " + i);
            newUser.setEmail("vulb" + i + "@example.com");
            newUser.setPassword("123456");
            newUser.setPhone("0123456789");
            newUser.setRole(Role.PASSENGER);
            newUser.setPassword(pass);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
                System.out.println("Inserted: " + i);
            }
        }
    }
}
