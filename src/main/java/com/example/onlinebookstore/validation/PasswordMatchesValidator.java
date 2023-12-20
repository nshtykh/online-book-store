package com.example.onlinebookstore.validation;

import com.example.onlinebookstore.dto.user.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto userRegistrationRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        return userRegistrationRequestDto.getPassword().equals(userRegistrationRequestDto.getRepeatPassword());
    }
}
