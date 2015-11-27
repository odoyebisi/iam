package ng.odo.web.form.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ng.odo.iamcore.datamodel.Identity;
import ng.odo.web.services.IdentityService;

@Component
public class IdentityFormValidator implements Validator{

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Identity.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Identity identity = (Identity) target;
		
		// check of empty fields in form
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.identityForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.identityForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.identityForm.email");
		
		// validate Identity form inputs
		if(identity.getFirstName().equalsIgnoreCase("none")){
			errors.rejectValue("firstName", "NotEmpty.identityForm.firstName");
		}
		
		if(identity.getFirstName().equalsIgnoreCase("none")){
			errors.rejectValue("lastName", "NotEmpty.identityForm.lastName");
		}

		//validate email
		if(!emailValidator.valid(identity.getEmail())){
			errors.rejectValue("email", "Pattern.identityForm.email");
		}
		
	}

}
