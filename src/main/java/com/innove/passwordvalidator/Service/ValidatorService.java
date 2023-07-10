package com.innove.passwordvalidator.Service;

import com.innove.passwordvalidator.VO.VerifyResult;

public interface ValidatorService {

	VerifyResult validatePassword(String password);
}
