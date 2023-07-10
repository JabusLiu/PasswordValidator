package com.innove.passwordvalidator;

import com.innove.passwordvalidator.Enums.StatusType;
import com.innove.passwordvalidator.Service.ValidatorService;
import com.innove.passwordvalidator.VO.VerifyResult;
import com.innove.passwordvalidator.Validator.CompositionValidator;
import com.innove.passwordvalidator.Validator.DuplicatedLetterValidator;
import com.innove.passwordvalidator.Validator.LengthValidator;
import jakarta.annotation.Resource;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordValidatorTests {

	@Autowired
	ValidatorService validatorService;

	@Resource
	LengthValidator lengthValidator;

	@Resource
	DuplicatedLetterValidator duplicatedLetterValidator;

	@Resource
	CompositionValidator compositionValidator;

	@Test
	@Order(1)
	public void PasswordLengthUnitTest() {
		SoftAssertions softAssertions = new SoftAssertions();

		String allPassStr = StatusType.AllPass.getWording();
		String hasFailStr = StatusType.HasFail.getWording();

		LengthValidator validator = lengthValidator;

		validator.setLength(5, 12);
		validator.setPassword("12345");
		VerifyResult result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		validator.setPassword("123456789012");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		//fail start
		validator.setPassword("");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("1234");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("1234567890123");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		softAssertions.assertAll();
	}

	@Test
	@Order(2)
	public void DuplicatedLetterUnitTest() {
		SoftAssertions softAssertions = new SoftAssertions();

		String allPassStr = StatusType.AllPass.getWording();
		String hasFailStr = StatusType.HasFail.getWording();

		DuplicatedLetterValidator validator = duplicatedLetterValidator;

		validator.setPassword("abcde");
		VerifyResult result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		validator.setPassword("abcdfghjk");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		//fail start
		validator.setPassword("");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("abdffhgs");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("hhfvchnk");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("qwfudfhss");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		softAssertions.assertAll();
	}

	@Test
	@Order(3)
	public void CompositionUnitTest() {
		SoftAssertions softAssertions = new SoftAssertions();

		String allPassStr = StatusType.AllPass.getWording();
		String hasFailStr = StatusType.HasFail.getWording();

		CompositionValidator validator = compositionValidator;

		validator.setPassword("abcd1234");
		VerifyResult result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		validator.setPassword("5678ghjk");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		validator.setPassword("842fgbd34");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		validator.setPassword("akb512cxb");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		//fail start
		validator.setPassword("");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("abcdef");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("123456");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("Abcde635");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		validator.setPassword("ab4#56");
		result = validator.validate();
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		softAssertions.assertAll();
	}

	@Test
	@Order(4)
	public void IntegrationTest(){

		String allPassStr = StatusType.AllPass.getWording();
		String hasFailStr = StatusType.HasFail.getWording();

		SoftAssertions softAssertions = new SoftAssertions();

		VerifyResult result = validatorService.validatePassword("abcd1234");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		result = validatorService.validatePassword("1234abcd");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		result = validatorService.validatePassword("123abcd123");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		result = validatorService.validatePassword("abcde12fghij");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		result = validatorService.validatePassword("1a2b3c4d5e");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		result = validatorService.validatePassword("a1b2c3d4e5");
		softAssertions.assertThat(result.getResult()).isEqualTo(allPassStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(3);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(0);

		//fail start
		result = validatorService.validatePassword("abc1");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(2);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		result = validatorService.validatePassword("aa123");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(2);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		result = validatorService.validatePassword("A123b");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(2);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(1);

		result = validatorService.validatePassword("a1bb");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(2);

		result = validatorService.validatePassword("Aa12");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(2);

		result = validatorService.validatePassword("Aaa12");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(1);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(2);

		result = validatorService.validatePassword("Aaa123asd4568d");
		softAssertions.assertThat(result.getResult()).isEqualTo(hasFailStr);
		softAssertions.assertThat(result.getPassedCount()).isEqualTo(0);
		softAssertions.assertThat(result.getFailedCount()).isEqualTo(3);

		softAssertions.assertAll();
	}

}
