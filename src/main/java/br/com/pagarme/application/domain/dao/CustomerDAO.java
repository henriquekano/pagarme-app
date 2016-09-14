package br.com.pagarme.application.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pagarme.application.domain.entity.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long>{

}
