package com.asu.alliancebank.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = NotValidPhoneNumber.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotValidInteger {
	/**
	 * Default message to display in case field contains invalid characters
	 * @return message of type Long 
	 */
	public abstract String message() default "Field should contain only numbers";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}




