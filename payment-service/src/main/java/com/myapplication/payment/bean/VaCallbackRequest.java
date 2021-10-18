package com.myapplication.payment.bean;

public class VaCallbackRequest {

	private String vaNumber;
	
	private String status;

	public String getVaNumber() {
		return vaNumber;
	}

	public void setVaNumber(String vaNumber) {
		this.vaNumber = vaNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
