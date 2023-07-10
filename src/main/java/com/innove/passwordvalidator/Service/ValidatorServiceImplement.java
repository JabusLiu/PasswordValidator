package com.innove.passwordvalidator.Service;

import com.innove.passwordvalidator.VO.VerifyResult;
import com.innove.passwordvalidator.Validator.CompositionValidator;
import com.innove.passwordvalidator.Validator.DuplicatedLetterValidator;
import com.innove.passwordvalidator.Validator.LengthValidator;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ValidatorServiceImplement implements ValidatorService{

	@Resource
	LengthValidator lengthValidator;

	@Resource
	DuplicatedLetterValidator duplicatedLetterValidator;

	@Resource
	CompositionValidator compositionValidator;

	@Override
	public VerifyResult validatePassword(String password) {

		LengthValidator validator = lengthValidator;
		validator.setLength(5, 12);
		validator.setNextValidator(duplicatedLetterValidator)
				.setNextValidator(compositionValidator);

		validator.setPassword(password);

		VerifyResult result = validator.validate();

		return result;
	}
}
