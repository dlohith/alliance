package com.asu.alliancebank.annotation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.asu.alliancebank.domain.impl.Role;


/**
 *  This class works as a validator for {@link NotEmptyRoleList}  annotation
 * 
 * @author Lohith Dwaraka
 *
 */
public class NotEmptyRoleListValidator implements ConstraintValidator<NotEmptyRoleList, List<Role>>{

	@Override
	public void initialize(NotEmptyRoleList arg0) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(List<Role> arg0, ConstraintValidatorContext arg1) {
		if((arg0 != null)&&(arg0.size()>0)){
			return true;
		}else if(arg0 == null){
			return false;
		}
		return false;
	}
}
