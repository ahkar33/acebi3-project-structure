package com.ace.web.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ace.web.pf.config.JwtUserFactory;
import com.ace.web.pf.datamodel.User;
import com.ace.web.pf.repository.UserRepository;
import com.ace.web.pf.service.interfaces.IUserService;

@Service(value = "userService")
public class UserService implements IUserService {

	@Autowired
	private UserRepository repo;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = repo.findByUserName(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return JwtUserFactory.create(user);
	}

	public User findByUserName(String userName) {
		return repo.findByUserName(userName);
	}

	public User save(User entity) {
		return repo.save(entity);
	}

	public User findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public String findPasswordById(Long id) {
		return repo.findPasswordById(id);
	}
}
