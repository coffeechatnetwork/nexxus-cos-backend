package com.nexxus.common.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Password strength validator.
 * Validates that password meets security requirements:
 * - Minimum 8 characters
 * - Contains at least one uppercase letter
 * - Contains at least one lowercase letter
 * - Contains at least one digit
 * - Contains at least one special character
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = 
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    
    // Common weak passwords to reject
    private static final String[] WEAK_PASSWORDS = {
        "password", "password123", "12345678", "qwerty123",
        "admin123", "letmein", "welcome123", "monkey123"
    };

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        
        // Check against weak passwords (case-insensitive)
        String lowerPassword = password.toLowerCase();
        for (String weak : WEAK_PASSWORDS) {
            if (lowerPassword.equals(weak.toLowerCase())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    "Password is too weak. Please choose a stronger password.")
                    .addConstraintViolation();
                return false;
            }
        }
        
        // Check pattern
        if (!pattern.matcher(password).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Password must be at least 8 characters and contain uppercase, lowercase, digit, and special character.")
                .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}
