package com.myapplication.product.controller;

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
import com.myapplication.product.model.Product;
import com.myapplication.product.model.ProductCategory;
import com.myapplication.product.service.ProductService;

@RestController
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/category/list", method = RequestMethod.POST, produces = "application/json")
	public Response<List<ProductCategory>> listProductCategory() {
		Response<List<ProductCategory>> resp = new Response<>();
		List<ProductCategory> data = null;
		try {
			data = productService.processListProductCategory();
			resp.setData(data);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public Response<List<Product>> listProduct(@RequestBody Product request) {
		Response<List<Product>> resp = new Response<>();
		List<Product> data = null;
		try {
			data = productService.processListProduct(request.getCategoryId());
			resp.setData(data);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json")
	public Response<Product> getProduct(@RequestBody Product request) {
		Response<Product> resp = new Response<>();
		Product data = null;
		try {
			data = productService.processGetProduct(request.getId());
			resp.setData(data);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}

}
