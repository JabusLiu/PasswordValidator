package com.innove.passwordvalidator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.innove.passwordvalidator.Enums.StatusType;
import com.innove.passwordvalidator.VO.VerifyResult;

public abstract class BaseValidator {

	protected String password = "";

	protected String rule = "";

	protected BaseValidator nextValidator;

	public BaseValidator(){}

	protected void setRule(String rule, Object... args){
		this.rule = String.format(rule, args);
	}

	public BaseValidator setNextValidator(BaseValidator validator){
		this.nextValidator = validator;
		return this.nextValidator;
	}

	public void setPassword(String password){
		this.password = password;
		if(Objects.nonNull(this.nextValidator)){
			this.nextValidator.setPassword(password);
		}
	}

	public VerifyResult validate(){
		return validate(new HashMap<>());
	}

	public VerifyResult validate(Map<String, List> resultMap){
		boolean isPass = this.isEmpty(this.password) ? false : this.check();

		resultMap = this.buildTestResult(resultMap, this.rule, isPass);

		if(Objects.nonNull(this.nextValidator)){
			return this.nextValidator.validate(resultMap);
		}else{
			return this.getResults(resultMap);
		}
	}

	public abstract boolean check();

	protected Map<String, List> buildTestResult(Map<String, List> resultMap, String rule, boolean isPass){
		StatusType statusType = isPass ? StatusType.Passed : StatusType.Failed;

		List resultList = resultMap.get(statusType.name());
		if(Objects.isNull(resultList)){
			resultList = new ArrayList();
			resultMap.put(statusType.name(), resultList);
		}

		resultList.add(resultList.size()+1 + ". " + rule);

		return resultMap;
	}

	protected VerifyResult getResults(Map<String, List> resultMap){
		VerifyResult result = new VerifyResult();

		int passedCount = resultMap.get(StatusType.Passed.name()) == null ? 0 : resultMap.get(StatusType.Passed.name()).size();
		int failedCount = resultMap.get(StatusType.Failed.name()) == null ? 0 : resultMap.get(StatusType.Failed.name()).size();

		StatusType statusType = failedCount == 0 ? StatusType.AllPass : StatusType.HasFail;

		result.setResult(statusType.getWording());
		result.setPassedCount(passedCount);
		result.setFailedCount(failedCount);
		result.setDetail(resultMap);

		return result;
	}

	private boolean isEmpty(String string){
		return string == null || string.isEmpty();
	}
}
