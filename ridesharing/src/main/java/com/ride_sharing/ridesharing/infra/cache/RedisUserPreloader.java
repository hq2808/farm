package com.ride_sharing.ridesharing.infra.cache;

import com.ride_sharing.ridesharing.model.User;
import com.ride_sharing.ridesharing.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUserPreloader {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;  // Spring sẽ tự động inject

    private static final String REDIS_USER_PREFIX = "user::";
    private static final int PRELOAD_LIMIT = 5000;

    @PostConstruct
    public void preloadTopUsersToRedis() {
        try {
            log.info("🚀 Start preloading top {} active users into Redis", PRELOAD_LIMIT);
            Page<User> topUsers = userRepository.findTopActiveUsers(PageRequest.of(0, PRELOAD_LIMIT));
            topUsers.getContent().forEach(user ->
                    redisTemplate.opsForValue().set(REDIS_USER_PREFIX + user.getEmail(), user) // Ghi vào Redis
            );
            log.info("✅ Successfully preloaded {} users into Redis", topUsers.getTotalPages());
        } catch (Exception e) {
            log.error("❌ Failed to preload users into Redis: {}", e.getMessage(), e);
        }
    }
}