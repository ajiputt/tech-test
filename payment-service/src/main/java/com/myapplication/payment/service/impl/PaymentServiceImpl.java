package com.myapplication.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapplication.payment.model.PaymentMethod;
import com.myapplication.payment.repository.PaymentMethodRepository;
import com.myapplication.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	


	@Override
	public List<PaymentMethod> processListPaymentMethod() {
		return paymentMethodRepository.findAll();
	}
	
}
