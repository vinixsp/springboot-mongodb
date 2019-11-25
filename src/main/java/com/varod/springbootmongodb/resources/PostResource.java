package com.varod.springbootmongodb.resources;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.varod.springbootmongodb.domain.Post;
import com.varod.springbootmongodb.resources.util.URL;
import com.varod.springbootmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@PathParam(value = "text") String text) {
		List<Post> posts = service.findByTitle(URL.decodeParam(text));
		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(value="/titlequery", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitleQuery(@PathParam(value = "text") String text) {
		List<Post> posts = service.findByTitle(URL.decodeParam(text));
		return ResponseEntity.ok().body(posts);
	}	
}
