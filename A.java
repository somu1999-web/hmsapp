package com.hmsapp_test;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class A {
    public static void main(String[] args) {
//      PasswordEncoder e = new BCryptPasswordEncoder();
//      System.out.println(e.encode("testing"));
        String encodedPwd = BCrypt.hashpw("testing", BCrypt.gensalt());
        System.out.println(encodedPwd);
    }
}
