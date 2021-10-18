package com.myapplication.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response<T> {

	private String responseCode;

	private String responseDescEn;

	private String responseDescId;

	private T data;
	
	@JsonIgnore
	public void setResponse(CommonResponse commonResponse) {
		this.responseCode = commonResponse.getCode();
		this.responseDescEn = commonResponse.getDescEn();
		this.responseDescId = commonResponse.getDescId();
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescEn() {
		return responseDescEn;
	}

	public void setResponseDescEn(String responseDescEn) {
		this.responseDescEn = responseDescEn;
	}

	public String getResponseDescId() {
		return responseDescId;
	}

	public void setResponseDescId(String responseDescId) {
		this.responseDescId = responseDescId;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
