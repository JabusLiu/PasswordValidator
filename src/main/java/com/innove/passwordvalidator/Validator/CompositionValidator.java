package com.innove.passwordvalidator.Validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CompositionValidator extends BaseValidator{

	private final String rule = "Lowercase letters and numerical digits only and with at least one of each.";

	private final String regex = "^(?!\\d+$)(?![a-z]+$)[0-9a-z]{0,}$";

	public CompositionValidator(){
		super();
		this.setRule(rule);
	}

	@Override
	public boolean check(){
		return this.password.matches(regex);
	}
}
