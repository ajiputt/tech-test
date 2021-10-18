package com.myapplication.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapplication.product.model.Product;
import com.myapplication.product.model.ProductCategory;
import com.myapplication.product.repository.ProductCategoryRepository;
import com.myapplication.product.repository.ProductRepository;
import com.myapplication.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductRepository productRepository;
	


	@Override
	public List<ProductCategory> processListProductCategory() {
		return productCategoryRepository.findAll();
	}
	
	@Override
	public List<Product> processListProduct(Integer categoryId) {
		if (categoryId == null) {
			return productRepository.findAll();
		} else {
			return productRepository.listProductByCategoryId(categoryId);
		}
	}

	@Override
	public Product processGetProduct(Integer id) {
		return productRepository.getOne(id);
	}

}
