package com.ride_sharing.ridesharing.mapper;

import com.ride_sharing.ridesharing.dto.UserDTO;
import com.ride_sharing.ridesharing.model.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}