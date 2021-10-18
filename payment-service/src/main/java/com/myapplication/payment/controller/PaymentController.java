package com.myapplication.payment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapplication.bean.CommonResponse;
import com.myapplication.bean.Response;
import com.myapplication.payment.bean.VaCallbackRequest;
import com.myapplication.payment.model.PaymentMethod;
import com.myapplication.payment.service.PaymentService;

@RestController
public class PaymentController {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public Response<List<PaymentMethod>> listProductCategory() {
		Response<List<PaymentMethod>> resp = new Response<>();
		List<PaymentMethod> data = null;
		try {
			
			data = paymentService.processListPaymentMethod();
			
			resp.setData(data);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public Response<List<PaymentMethod>> callbackVa(@RequestBody VaCallbackRequest req) {
		Response<List<PaymentMethod>> resp = new Response<>();
		List<PaymentMethod> data = null;
		try {
			
			data = paymentService.processListPaymentMethod();
			
			resp.setData(data);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}

}
