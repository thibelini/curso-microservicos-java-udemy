package com.thiagobelini.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Criptografia {

    public static String cripto(String texto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = encoder.encode(texto).toString();
        return senha;
    }

    public static void main(String[] args) {
        System.out.println(cripto("belinha"));
    }
}
