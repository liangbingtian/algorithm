package com.liang.argorithm.estest;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author liangbingtian
 * @date 2024/06/16 上午9:53
 */
public class PasswordTest {


    @Test
    public void testEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String string = passwordEncoder.encode("admin123");
        System.out.println(string);
    }


    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);

    }
}