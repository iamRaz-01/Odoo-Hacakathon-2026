package com.transitops.validator;
import com.transitops.exception.ConflictException;
import org.springframework.stereotype.Component;
@Component
public class PasswordValidator {
    public void validate(String password) {
        if (password == null || password.length() < 12 || password.length() > 128 || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
            throw new ConflictException("Password must be 12-128 characters and contain at least one letter and one digit.");
        }
    }
}
