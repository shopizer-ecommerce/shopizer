package com.salesmanager.shop.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validates values of a String used as payload in REST service
 * Solution taken from https://funofprograming.wordpress.com/2016/09/29/java-enum-validator/
 * @author c.samson
 *
 */
public class EnumValidator implements ConstraintValidator<Enum, String>
{
    private Enum annotation;
 

    public void initialize(Enum annotation)
    {
        this.annotation = annotation;
    }
 

    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext)
    {
        boolean result = false;
         
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
         
        if(enumValues != null)
        {
            for(Object enumValue:enumValues)
            {
                if(valueForValidation.equals(enumValue.toString()) 
                   || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString())))
                {
                    result = true; 
                    break;
                }
            }
        }
         
        return result;
    }
}
