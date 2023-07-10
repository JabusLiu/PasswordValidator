package com.innove.passwordvalidator.Enums;

public enum StatusType {

	Passed("(Passed)"),
	Failed("(Failed)"),
	AllPass("Verified successfully."),
	HasFail("Verify Failure.");

	private String wording;
	StatusType(String wording){
		this.wording = wording;
	}

	public String getWording(){
		return this.wording;
	}
}
