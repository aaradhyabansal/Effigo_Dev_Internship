package com.Aaradhya.SpringBoot.myTodoApp.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

//    InMemoryUserDetailsManager
//    InmemoryUserDetailsManager(UserDetails... user)

        @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
            String username = "Aaradhya";
            String password = "abcd1234";
            String role = "ADMIN";
            UserDetails userDetails = createNewUser(username, password, role);

            return new   InMemoryUserDetailsManager(userDetails);

        }

    private UserDetails createNewUser(String username, String password, String role) {
        Function<String, String> passwordEncoder=input->passwordEncoder().encode(input);

        UserDetails userDetails=  User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", role).build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
            return  new BCryptPasswordEncoder();
        }
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
            http.authorizeHttpRequests(
                    auth->auth.anyRequest().authenticated()
            );
            http.formLogin(withDefaults());
            http.csrf().disable();
            http.headers().frameOptions().disable();
            
            return http.build();

        }
}

