package com.ciazhar.postgres.config;

import com.ciazhar.postgres.model.entity.User;
import com.ciazhar.postgres.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JpaConfig {

    private final UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorProvider() {
        // Assuming you have some way to provide the current user
        // You can fetch the current user from SecurityContext, session, etc.
        return () -> userRepository.findById(1);
    }
}
