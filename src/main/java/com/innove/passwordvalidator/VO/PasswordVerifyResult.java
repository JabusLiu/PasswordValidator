package com.innove.passwordvalidator.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PasswordVerifyResult extends Result{

	private String password;

	private Result result;
}
