package com.varod.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varod.springbootmongodb.domain.User;
import com.varod.springbootmongodb.dto.UserDTO;
import com.varod.springbootmongodb.repository.UserRepository;
import com.varod.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		if (user.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado"); 
		}
		return user.get();
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User dbUser = findById(obj.getId());
		updateUserData(dbUser, obj);
		return repo.save(dbUser);
	}
	
	private void updateUserData(User dbUser, User obj) {
		dbUser.setName(obj.getName());
		dbUser.setEmail(obj.getEmail());
	}

	public User fromDto(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}
