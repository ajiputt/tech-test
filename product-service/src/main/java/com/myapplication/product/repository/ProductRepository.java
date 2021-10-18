package com.myapplication.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapplication.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
//	public Product findProductById(@Param("id") Integer id);
	
	@Query("select a from Product a where a.categoryId = :categoryId")
	public List<Product> listProductByCategoryId(@Param("categoryId") Integer categoryId);

}
