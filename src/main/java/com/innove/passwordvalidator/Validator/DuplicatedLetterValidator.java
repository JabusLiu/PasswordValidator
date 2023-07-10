package com.innove.passwordvalidator.Validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DuplicatedLetterValidator extends BaseValidator{

	private final String rule = "Not contain any sequence of characters immediately followed by the same sequence.";

	public DuplicatedLetterValidator(){
		super();
		this.setRule(rule);
	}

	@Override
	public boolean check(){
		boolean isPass = true;
		for(int idx = 0; idx < this.password.length() -1; idx++){
			if(this.password.charAt(idx) == this.password.charAt(idx+1)){
				isPass = false;
				break;
			}
		}
		return isPass;
	}

}
