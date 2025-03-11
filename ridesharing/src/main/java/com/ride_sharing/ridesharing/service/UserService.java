package com.ride_sharing.ridesharing.service;

import com.ride_sharing.ridesharing.dto.LoginRequest;
import com.ride_sharing.ridesharing.dto.LoginResponse;
import com.ride_sharing.ridesharing.dto.UserDTO;
import com.ride_sharing.ridesharing.model.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    void deleteUser(Long id);
    LoginResponse login(LoginRequest loginRequest);
}
