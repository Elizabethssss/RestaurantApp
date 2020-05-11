package com.restaurant.service.validator.impl;

import com.restaurant.domain.User;
import com.restaurant.service.validator.CreditCardValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class CreditCardValidatorImplTest {
    private final ResourceBundle bundle = ResourceBundle.getBundle("locale", Locale.getDefault());
    public static final String RIGHT_CARD_NUMBER = "378282246310005";
    public static final String RIGHT_CVV = "378";
    public static final String RIGHT_MONTH = "03";
    public static final String RIGHT_YEAR = "24";

    private final CreditCardValidator validator = new CreditCardValidatorImpl();

    @Test
    public void validateMapShouldBeEmpty() {
        final Map<String, String> result = validator.validate(RIGHT_CARD_NUMBER, RIGHT_MONTH, RIGHT_YEAR, RIGHT_CVV, bundle);

        assertTrue(result.isEmpty());
    }

    @Test
    public void validateWithEmptyFieldsMapShouldBeFull() {
        final Map<String, String> result = validator.validate("", RIGHT_MONTH, "", "", bundle);

        assertEquals(3, result.size());
    }

    @Test
    public void validateWithWrongCardNumber() {
        final Map<String, String> result = validator.validate(RIGHT_CARD_NUMBER + 1, "",
                RIGHT_YEAR, "dfs", bundle);

        assertEquals(3, result.size());
    }

    @Test
    public void validateWithWrongInputDateAndCardNumber() {
        final Map<String, String> result = validator.validate(RIGHT_CARD_NUMBER + "a", "01",
                "20", "dfs", bundle);

        assertEquals(3, result.size());
    }

}