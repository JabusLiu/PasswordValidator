package com.innove.passwordvalidator.Validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LengthValidator extends BaseValidator{

	private int minLength;
	private int maxLength;

	private String rule = "Between %d and %d characters in length.";

	public LengthValidator(){
		super();
	}

	public void setLength(int minLength, int maxLength){
		this.minLength = 5;
		this.maxLength = 12;
		this.setRule(rule, minLength, maxLength);
	}

	@Override
	public boolean check(){
		return this.password.length() >= minLength && this.password.length() <= maxLength;
	}
}
