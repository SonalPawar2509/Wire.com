package com.wire.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Credentials {

    private String username;
    private String password;
    private String deeplink;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDeeplink() {
        return deeplink;
    }
    
    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    // Load credentials from a file
    public static Credentials loadCredentials() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/test/resources/credentials.json"), Credentials.class);
    }
}
