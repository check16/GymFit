package com.asanast.gymfit.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asanast.gymfit.pojo.VO.PasswordForm;


@Component
public class PasswordFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordForm pf = (PasswordForm) target;
		if(!pf.getClave().equals(pf.getRepiteClave())){
            errors.rejectValue("clave","Las claves no coinciden");
        }
		if(pf.getClave().isEmpty()) {
			errors.rejectValue("clave","No puede estar vacío");
		}
		if(pf.getRepiteClave().isEmpty()) {
			errors.rejectValue("repiteClave","No puede estar vacío");
		}
		if(pf.getClave().length() < 4) {
			errors.rejectValue("clave","Debe tener 4 caracteres como mínimo");
		}
		if(pf.getRepiteClave().length() < 4) {
			errors.rejectValue("repiteClave","Debe tener 4 caracteres como mínimo");
		}
		
	}

}
