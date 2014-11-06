package com.asu.alliancebank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = IsEmailValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsEmail {
	public abstract String message() default "Field is empty, please fill.";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
