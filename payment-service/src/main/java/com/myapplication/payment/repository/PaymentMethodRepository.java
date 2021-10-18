package com.myapplication.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapplication.payment.model.PaymentMethod;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
	
//	public Product findProductById(@Param("id") Integer id);
	
	@Query("select a from Product a where a.categoryId = :categoryId")
	public List<PaymentMethod> listProductByCategoryId(@Param("categoryId") Integer categoryId);

}
