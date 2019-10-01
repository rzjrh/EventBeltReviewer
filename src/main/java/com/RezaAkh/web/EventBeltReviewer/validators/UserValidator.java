package com.RezaAkh.web.EventBeltReviewer.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.RezaAkh.web.EventBeltReviewer.models.EventUser;
import com.RezaAkh.web.EventBeltReviewer.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	@Autowired
	private UserRepository uRepo;
	@Override
    public boolean supports(Class<?> clazz) {
        return EventUser.class.equals(clazz);
    }
	@Override
    public void validate(Object target, Errors errors) {
        EventUser user = (EventUser) target;
        
        if(this.uRepo.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Unique");
        }
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
