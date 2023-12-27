package com.example.securityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http)throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var user1=User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var user2=User.withUsername("mary")
                .password("12345")
                .authorities("read")
                .build();
        InMemoryUserDetailsManager memoryUserDetailsManager=
                new InMemoryUserDetailsManager();
        memoryUserDetailsManager.createUser(user1);
        memoryUserDetailsManager.createUser(user2);
        http.userDetailsService(memoryUserDetailsManager);


        return http.build();
    }
    //Bean
    public UserDetailsService userDetailsService(){

        var user1=User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var user2=User.withUsername("mary")
                .password("12345")
                .authorities("read")
                .build();
        InMemoryUserDetailsManager memoryUserDetailsManager=
                new InMemoryUserDetailsManager();
        memoryUserDetailsManager.createUser(user1);
        memoryUserDetailsManager.createUser(user2);
        return memoryUserDetailsManager;
    }
}
