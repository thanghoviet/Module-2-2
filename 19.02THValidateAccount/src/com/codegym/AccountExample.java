package com.codegym;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountExample {
    private static Pattern pattern;
    private Matcher matcher;

    private static final String ACCOUNT_EXAMPLE="^[_a-z0-9]{6,}$";
    public AccountExample() {
        pattern = Pattern.compile(ACCOUNT_EXAMPLE);
    }

    public boolean validate(String regex) {
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
