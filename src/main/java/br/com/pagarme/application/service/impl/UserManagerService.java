package br.com.pagarme.application.service.impl;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pagarme.application.domain.dao.CustomerDAO;
import br.com.pagarme.application.domain.dao.UserDAO;
import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.domain.entity.User;

@Service
public class UserManagerService implements UserDetailsService {

	private final UserDAO userDAO;
	private final CustomerDAO customerDAO;
	
	@Autowired
	public UserManagerService(UserDAO userDAO, CustomerDAO customerDAO) {
		this.userDAO = userDAO;
		this.customerDAO = customerDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Credenciais incorretas");
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), Arrays.asList());
		return userDetails;
	}
	
	public Customer findCurrentCustomer(Principal principal){
		User user = userDAO.findByUsername(principal.getName());
		Customer customer = customerDAO.findByUser(user);
		return customer;
	}

}
