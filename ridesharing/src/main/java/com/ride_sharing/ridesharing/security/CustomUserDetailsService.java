package com.ride_sharing.ridesharing.security;

import com.ride_sharing.ridesharing.model.User;
import com.ride_sharing.ridesharing.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service  // Quan trọng: Đánh dấu đây là một Bean
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()) // Cần chuyển đổi role sang dạng phù hợp
                .build();
    }
}