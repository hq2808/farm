package com.ride_sharing.ridesharing.dto;

import com.ride_sharing.ridesharing.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {
    private String name;
    private String email;
    private String phone;
    private Role role;
}
