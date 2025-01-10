package com.example.Task.Management.utils;
import java.util.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    private <K, V> String generateToken(HashMap<K,V> kvHashMap, UserDetails userDetails) {
    }
}
