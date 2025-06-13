package com.ride_sharing.ridesharing.service;

import com.ride_sharing.ridesharing.model.User;
import com.ride_sharing.ridesharing.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserCacheService {

    private static final Duration USER_TTL = Duration.ofMinutes(15);
    private static final Duration PRELOAD_TTL = Duration.ofHours(1);

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    private String getCacheKey(String email) {
        return "user::" + email;
    }

    public Optional<User> getUserFromCache(String email) {
        Object cached = redisTemplate.opsForValue().get(getCacheKey(email));
        if (cached instanceof User user) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void cacheUser(User user, Duration ttl) {
        redisTemplate.opsForValue().set(getCacheKey(user.getEmail()), user, ttl);
    }

    public void cacheUserOnLogin(User user) {
        cacheUser(user, USER_TTL);
    }

    @PostConstruct
    public void preloadTopActiveUsers() {
        Page<User> topUsers = userRepository.findTopActiveUsers(PageRequest.of(0, 5000));
        topUsers.forEach(user ->
                cacheUser(user, PRELOAD_TTL)
        );
        System.out.println("✅ Preloaded 5000 active users into Redis");
    }


//    @PostConstruct
    public void clearUserCache() {
        Set<String> keysToDelete = new HashSet<>();

        redisTemplate.execute((RedisCallback<Void>) connection -> {
            ScanOptions options = ScanOptions.scanOptions().match("user::*").count(1000).build();
            Cursor<byte[]> cursor = connection.scan(options);

            while (cursor.hasNext()) {
                keysToDelete.add(new String(cursor.next()));
            }
            return null;
        });

        if (!keysToDelete.isEmpty()) {
            redisTemplate.delete(keysToDelete);
        }
    }

    public void evictUser(String email) {
        redisTemplate.delete(getCacheKey(email));
    }
}
