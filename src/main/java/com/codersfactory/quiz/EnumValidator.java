package com.codersfactory.quiz;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValue, Enum<?>> {

    private EnumValue annotation;

    @Override
    public void initialize(EnumValue annotation) {
        this.annotation = annotation;
    }



    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        Enum<?>[] enumValues = enumClass.getEnumConstants();
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value.name())) {
                return true;
            }
        }
        return false;
    }
}