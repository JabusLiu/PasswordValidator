package com.innove.passwordvalidator.Controller;

import com.innove.passwordvalidator.Service.ValidatorService;
import com.innove.passwordvalidator.VO.PasswordVerifyResult;
import com.innove.passwordvalidator.VO.Result;
import com.innove.passwordvalidator.VO.VerifyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/password")
@Scope("prototype")
public class ValidatorController {

	@Autowired
	ValidatorService validatorService;

	@PostMapping("/verify")
	public Result passwordVerify(@RequestParam(required = true) String password){

		VerifyResult result = validatorService.validatePassword(password);

		PasswordVerifyResult verifyResult = new PasswordVerifyResult();
		verifyResult.setPassword(password);
		verifyResult.setResult(result);

		return verifyResult;
	}
}
