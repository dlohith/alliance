package com.asu.alliancebank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * This annotation checks if the specified field is used for cross scripting
 * 				  Validated by {@link NotInvalidStringValidator}
 * 
 * @author      : Lohith Dwaraka
 *
 */
@Constraint(validatedBy = NotInvalidStringEncryptValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInvalidEncryptString {
	/**
	 * Default message to display in case field contains invalid characters
	 * @return message of type String 
	 */
	public abstract String message() default "Field contains invalid characters, please correct it.";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};

}
