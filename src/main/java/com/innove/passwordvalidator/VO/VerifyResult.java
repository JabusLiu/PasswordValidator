package com.innove.passwordvalidator.VO;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VerifyResult extends Result{

	private String result;

	private int passedCount = 0;

	private int failedCount = 0;

	private Map<String, List> detail;

}
