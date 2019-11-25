package com.varod.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varod.springbootmongodb.domain.Post;
import com.varod.springbootmongodb.repository.PostRepository;
import com.varod.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		if (post.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado.");
		}
		return post.get();
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContainsIgnoreCase(text);
	}
	
	public List<Post> findByTitleQuery(String text) {
		return repo.findByTitle(text);
	}
}
