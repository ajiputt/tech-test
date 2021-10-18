package com.myapplication.payment.service;

import java.util.List;

import com.myapplication.payment.model.PaymentMethod;

public interface PaymentService {
	
	public List<PaymentMethod> processListPaymentMethod();

}
