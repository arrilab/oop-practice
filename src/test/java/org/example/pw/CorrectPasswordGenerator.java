package org.example.pw;

import org.example.PasswordGeneratePolicy;

public class CorrectPasswordGenerator implements PasswordGeneratePolicy {
    @Override
    public String generatePassword() {
        return "aaabbbcc"; // 8글자
    }
}
