package br.com.pagarme.application.domain.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.domain.entity.Transaction;


@Repository
public interface TransactionDAO extends CrudRepository<Transaction, Long>{

	@Transactional
    Long deleteByTransactionId(String transactionId);
	
	@Transactional
	Iterable<Transaction> findByCustomerAndCancelled(Customer customer, Boolean cancelled);
	
	@Transactional
	List<Transaction> findByCustomer(Customer customer);
	
	@Transactional
	Transaction findOneByTransactionId(String transactionId);
}
