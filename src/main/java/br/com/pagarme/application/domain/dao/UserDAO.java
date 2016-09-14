package br.com.pagarme.application.domain.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.pagarme.application.domain.entity.User;

public interface UserDAO extends CrudRepository<User, Long> {

	@Transactional
	User findByUsername(String username);
}
