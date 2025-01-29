package com.split.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean email(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Email formatı için düzenli ifade
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Pattern ve Matcher kullanılarak kontrol
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
