package com.restaurant.service.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncryptorTest {
    private PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    @Test
    public void encrypt() {
        final String password = "password";
        final String encoded = passwordEncryptor.encrypt("liza");
        assertNotEquals(password, encoded);
    }
}