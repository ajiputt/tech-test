package com.myapplication.product.service;

import java.util.List;

import com.myapplication.product.model.Product;
import com.myapplication.product.model.ProductCategory;

public interface ProductService {
	
	public List<ProductCategory> processListProductCategory();
	
	public List<Product> processListProduct(Integer categoryId);
	
	public Product processGetProduct(Integer id);

}
