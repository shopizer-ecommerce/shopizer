package com.salesmanager.shop.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validates values of a String used as payload in REST service
 * Solution taken from https://funofprograming.wordpress.com/2016/09/29/java-enum-validator/
 * @author c.samson
 *
 */
public class EnumValidator implements ConstraintValidator<Enum, String> {
    private Enum annotation;

    @Override
    public void initialize(Enum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues == null) {
            return false;
        }
        for (Object enumValue : enumValues) {
            if (isEnumValueValid(valueForValidation, enumValue)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEnumValueValid(String valueForValidation, Object enumValue) {
        return isEnumValueEqual(valueForValidation, enumValue)
                || (isIgnoreCase() && isEnumValueEqualIgnoreCase(valueForValidation, enumValue));
    }

    private boolean isEnumValueEqual(String valueForValidation, Object enumValue) {
        return valueForValidation.equals(enumValue.toString());
    }

    private boolean isEnumValueEqualIgnoreCase(String valueForValidation, Object enumValue) {
        return isIgnoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString());
    }

    private boolean isIgnoreCase() {
        return this.annotation.ignoreCase();
    }
}
