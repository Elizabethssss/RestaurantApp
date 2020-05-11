package com.restaurant.service.validator;

import java.util.Map;
import java.util.ResourceBundle;

public interface CreditCardValidator {
    Map<String, String> validate(String cardNumber, String expiredMonth, String expiredYear,
                                 String cvv, ResourceBundle bundle);
}
