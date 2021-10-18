package com.myapplication.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapplication.payment.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
	
	@Query("select a from Transaction a where a.userId = :userId")
	public List<Transaction> listTransactionByUserId(@Param("userId") String userId);

}
