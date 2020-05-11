package com.restaurant.service.validator.impl;

import com.restaurant.service.validator.CreditCardValidator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CreditCardValidatorImpl implements CreditCardValidator {
    private static final String CARD_NUMBER_INPUT = "[0-9]{13,16}";
    private static final String CARD_CVV_INPUT = "[0-9]{3}";
    private static final String CARD_MONTH_INPUT = "(0[1-9]|1[0-2])";
    private static final String CARD_YEAR_INPUT = "([2-3][0-9])";

    @Override
    public Map<String, String> validate(String cardNumber, String expiredMonth, String expiredYear,
                                        String cvv, ResourceBundle bundle) {
        final String emptyMsg = bundle.getString("empty.field");
        Map<String, String> errorMessages = new HashMap<>();
        if (cardNumber.equals("")) {
            errorMessages.put("cardNumberError", emptyMsg);
        }
        else if(!cardNumber.matches(CARD_NUMBER_INPUT)) {
            errorMessages.put("cardNumberError", bundle.getString("wrong.input"));
        }
        else if(!validateCreditCardNumber(cardNumber)) {
            errorMessages.put("cardNumberError", bundle.getString("wrong.card.number"));
        }
        if(expiredMonth.equals("") || expiredYear.equals("")) {
            errorMessages.put("dateError", emptyMsg);
        }
        else if(!validateDateInput(expiredMonth, expiredYear)) {
            errorMessages.put("dateError", bundle.getString("wrong.date"));
        }
        if (cvv.equals("")) {
            errorMessages.put("cvvError", emptyMsg);
        }
        else if (!cvv.matches(CARD_CVV_INPUT)) {
            errorMessages.put("cvvError", bundle.getString("wrong.input"));
        }
        return errorMessages;
    }

    public boolean validateDateInput(String month, String year) {
        if (validateMonthInput(month) && validateYearInput(year)) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            calendar.set(Integer.parseInt(year) + 2000, Integer.parseInt(month) - 1, 1);
            Date myDate = calendar.getTime();
            return date.compareTo(myDate) < 0;
        }
        return false;
    }

    public boolean validateMonthInput(String month) {
        return month.matches(CARD_MONTH_INPUT);
    }

    public boolean validateYearInput(String year) {
        return year.matches(CARD_YEAR_INPUT);
    }

    public boolean validateCreditCardNumber(String number) {
        int[] ints = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            ints[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i -= 2) {
            int j = ints[i];
            j *= 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum % 10 == 0;
    }
}
