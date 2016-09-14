package br.com.pagarme.application.domain.dao;

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
	Iterable<Transaction> findByCustomer(Customer customer);
}
