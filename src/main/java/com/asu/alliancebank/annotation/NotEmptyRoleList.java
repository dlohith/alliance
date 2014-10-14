package com.asu.alliancebank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = NotEmptyRoleListValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyRoleList {

	/**
	 * Default message to display in case field is empty
	 * @return message of type String 
	 */
	public abstract String message() default "No role was selected, please select one";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
